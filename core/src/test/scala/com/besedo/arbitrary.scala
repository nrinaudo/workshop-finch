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
}
