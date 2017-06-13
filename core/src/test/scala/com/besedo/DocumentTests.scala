package com.besedo

import arbitrary._
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class DocumentTests extends FunSuite with GeneratorDrivenPropertyChecks {
  test("Moderation should behave as expected for profiles") {
    forAll { p: Document.Profile ⇒
      if(p.age >= 18) assert(moderate(p) == Decision.Accept(p.id))
      else            assert(moderate(p) == Decision.Reject(p.id))
    }
  }

  test("Moderation should behave as expected for ads") {
    forAll { a: Document.Ad ⇒
      if(a.price <= 1000F) assert(moderate(a) == Decision.Accept(a.id))
      else                 assert(moderate(a) == Decision.Reject(a.id))
    }
  }
}
