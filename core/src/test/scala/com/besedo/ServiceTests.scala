package com.besedo

import arbitrary._
import io.finch._
import io.finch.circe._
import JsonTests._
import org.scalatest.FunSuite
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class ServiceTests extends FunSuite with GeneratorDrivenPropertyChecks {
  def remoteModerate(d: Document): Decision =
    Service.moderation(Input.post("/moderate").withBody[Application.Json](d))
      .awaitOutputUnsafe().get.value

  test("Remote moderation should yield the expected resutls") {
    forAll { d: Document ⇒ assert(moderate(d) == remoteModerate(d)) }
  }
}
