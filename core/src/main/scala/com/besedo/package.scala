package com

package object besedo {
  /** Basic moderation rules.
    *
    * Rejects [[Document.Ad ads]] with a price higher than `1000F` and [[Document.Profile profiles]] with an age below
    * 18.
    */
  def moderate(d: Document): Decision = d match {
    case Document.Ad(id, _, price) if price > 1000F  ⇒ Decision.Reject(id)
    case Document.Profile(id, _, _, age) if age < 18 ⇒ Decision.Reject(id)
    case Document(id)                                ⇒ Decision.Accept(id)
  }
}
