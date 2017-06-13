package com.besedo

import java.util.UUID

/** Documents that can processed by our system.
  *
  * A document is either an [[Document.Ad ad]] or a [[Document.Profile profile]].
  */
sealed trait Document extends Product with Serializable {
  /** Identifier of the document. */
  def id: UUID
}

object Document {
  def unapply(d: Document): Option[UUID] = Some(d.id)

  final case class Ad(id: UUID, desc: String, price: Float) extends Document
  final case class Profile(id: UUID, teaser: String, desc: String, age: Int) extends Document
}
