package com.besedo

import org.scalacheck._
import org.scalacheck.Arbitrary.{arbitrary => arb}

object arbitrary {
  // - Arbitrary instances of Document ---------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------
  implicit val arbAd: Arbitrary[Document.Ad] = Arbitrary {
    for {
      id    ← Gen.uuid
      desc  ← arb[String]
      price ← arb[Float].map(math.abs)
    } yield Document.Ad(id, desc, price)
  }

  implicit val arbProfile: Arbitrary[Document.Profile] = Arbitrary {
    for {
      id     ← Gen.uuid
      teaser ← arb[String]
      desc   ← arb[String]
      age    ← arb[Int].map(math.abs)
    } yield Document.Profile(id, teaser, desc, age)
  }

  implicit val arbDocument: Arbitrary[Document] = Arbitrary(Gen.oneOf(arbAd.arbitrary, arbDocument.arbitrary))


  // - Arbitrary instances of Decision ---------------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------
  implicit val arbAccept: Arbitrary[Decision.Accept] = Arbitrary(Gen.uuid.map(Decision.Accept))
  implicit val arbReject: Arbitrary[Decision.Reject] = Arbitrary(Gen.uuid.map(Decision.Reject))

  implicit val arbDecision: Arbitrary[Decision] = Arbitrary(Gen.oneOf(arbAccept.arbitrary, arbReject.arbitrary))
}
