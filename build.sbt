def baseSettings = Seq(
  scalaVersion := "2.12.2",
  name         := "finch-workshop",
  organization := "com.besedo"
)

lazy val root = Project(id = "basic-finch", base = file("."))
  .settings(moduleName := "root")
  .settings(baseSettings)
  .aggregate(core, slides)

lazy val core = project
  .settings(baseSettings)
  .settings(libraryDependencies ++= Seq(
    "com.github.finagle" %% "finch-core"        % Versions.finch,
    "com.github.finagle" %% "finch-circe"       % Versions.finch,
    "com.github.finagle" %% "finagle-http-auth" % Versions.finagleAuth,
    "io.circe"           %% "circe-core"        % Versions.circe,
    "io.circe"           %% "circe-parser"      % Versions.circe,
    "org.scalatest"      %% "scalatest"         % Versions.scalatest    % "test",
    "org.scalacheck"     %% "scalacheck"        % Versions.scalacheck   % "test"
  ))

val tutDirName = settingKey[String]("tut output directory")

lazy val slides = project
  .enablePlugins(TutPlugin)
  .enablePlugins(SitePlugin)
  .settings(tutDirName := "./")
  .settings(addMappingsToSiteDir(tut, tutDirName))
  .settings(baseSettings)
  .dependsOn(core)
  .settings(includeFilter in SitePlugin.autoImport.makeSite :=
    "*.yml" | "*.md" | "*.html" | "*.css" | "*.png" | "*.jpg" | "*.gif" | "*.js" | "*.eot" | "*.svg" | "*.ttf" |
    "*.woff" | "*.woff2" | "*.otf"
  )
