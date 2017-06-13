package com.besedo

import java.util.UUID

/** Results of a moderation process.
  *
  * A decision is either [[Decision.Accept accept]] or [[Decision.Reject reject]].
  */
sealed abstract class Decision extends Product with Serializable {
  /** Identifier of the moderated document. */
  def id: UUID
}

object Decision {
  final case class Reject(id: UUID) extends Decision
  final case class Accept(id: UUID) extends Decision
}
