package com.besedo

import io.circe._
import java.util.UUID

object json {
  /** Decodes a JSON node into a function from `UUID` to [[Document]].
    *
    * This is me being a bit fancy. Whatever the type of a document, you always need to:
    * - get its `type` field, which lets you know whether you're working with a [[Document.Ad]] or [[Document.Profile]].
    * - get its `id` field
    *
    * This decoder will analyse the `type` field and return a function that, for a given `UUID`, builds the document
    * described by the JSON input.
    */
  implicit private val builderDecoder: Decoder[UUID ⇒ Document] = {
    val adDecoder: Decoder[UUID ⇒ Document] =
      Decoder.forProduct2("price", "body") { (price: Float, body: String) ⇒
        id ⇒ Document.Ad(id, body, price)
      }

    val profileDecoder: Decoder[UUID ⇒ Document] =
      Decoder.forProduct3("teaser", "body", "age") { (teaser: String, body: String, age: Int) ⇒
        id ⇒ Document.Profile(id, teaser, body, age)
      }

    Decoder.instance { cursor ⇒
      cursor.get[String]("type").flatMap {
        case "ad"      ⇒ adDecoder(cursor)
        case "profile" ⇒ profileDecoder(cursor)
        case error     ⇒ Left(DecodingFailure(s"Unknown document type: $error", cursor.history))
      }
    }
  }

  /** Decodes a JSON node as a [[Document]]. */
  implicit val documentDecoder: Decoder[Document] = Decoder.instance { cursor ⇒
    for {
      id      ← cursor.get[UUID]("id")
      builder ← cursor.as[UUID ⇒ Document]
    } yield builder(id)
  }

  /** Encodes a [[Decision]] as a JSON node. */
  implicit val decisionEncoder: Encoder[Decision] = Encoder.forProduct2("id", "decision") {
    case Decision.Accept(id) ⇒ (id, "accept")
    case Decision.Reject(id) ⇒ (id, "reject")
  }
}
