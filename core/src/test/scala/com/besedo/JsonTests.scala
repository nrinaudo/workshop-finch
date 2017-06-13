package com.besedo

import arbitrary._
import com.besedo.json._
import io.circe._
import io.circe.syntax._
import java.util.UUID
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class JsonTests extends FunSuite with GeneratorDrivenPropertyChecks {
  import JsonTests._

  test("Ads encoding and decoding should be equivalent to identity") {
    forAll { a: Document.Ad ⇒
      assert(a.asJson.as[Document] == Right(a))
    }
  }

  test("Profiles encoding and decoding should be equivalent to identity") {
    forAll { p: Document.Profile ⇒
      assert(p.asJson.as[Document] == Right(p))
    }
  }

  test("Decision encoding and decoding should be equivalent to identity") {
    forAll { d: Decision ⇒
      assert(d.asJson.as[Decision] == Right(d))
    }
  }
}

object JsonTests {
  implicit val adEncoder: Encoder[Document.Ad] = Encoder.forProduct4("type", "id", "price", "body") { ad ⇒
    ("ad", ad.id, ad.price, ad.desc)
  }

  implicit val profileEncoder: Encoder[Document.Profile] =
    Encoder.forProduct5("type", "id", "teaser", "body", "age") { p ⇒
      ("profile", p.id, p.teaser, p.desc, p.age)
    }

  implicit val documentEncoder: Encoder[Document] = Encoder.instance {
    case a@Document.Ad(_, _, _)         ⇒ adEncoder(a)
    case p@Document.Profile(_, _, _, _) ⇒ profileEncoder(p)
  }

  implicit val decisionDecoder: Decoder[Decision] = Decoder.instance { cursor ⇒
    cursor.get[UUID]("id").flatMap { id ⇒
      cursor.get[String]("decision").flatMap {
        case "accept" ⇒ Right(Decision.Accept(id))
        case "reject" ⇒ Right(Decision.Reject(id))
        case error    ⇒ Left(DecodingFailure(s"Unknown decision: $error", cursor.history))
      }
    }
  }


}
