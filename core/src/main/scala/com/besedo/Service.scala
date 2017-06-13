package com.besedo

import com.twitter.finagle.Http
import com.twitter.util.Await
import com.twitter.finagle.http.BasicAuth
import io.finch._
import io.finch.circe._
import json._

// Note that I would much rather Service extend App and get rid of the Main class, but this has unfortunate side effects
// on initialisation order - the moderation endpoint would not be initialised when tests call it.
object Service {
  // Basic authentication.
  val auth: BasicAuth.Server = BasicAuth.serverFromCredentials("besedo", "DrofKanoi")

  // Moderation endpoint.
  val moderation: Endpoint[Decision] = post("moderate" :: jsonBody[Document])(moderate _ andThen Ok)

  // Hooks everything into an actual service.
  def start(port: Int) = Await.ready(Http.server.serve(s":$port", auth.andThen(moderation.toService)))
}
