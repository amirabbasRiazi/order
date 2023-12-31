ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "project-workout"
  )


libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "org.postgresql" % "postgresql" % "42.2.5",
  "com.github.tminglei" %% "slick-pg" % "0.20.4",
  "com.github.tminglei" %% "slick-pg_play-json" %"0.20.4",
 "org.scalatest" %% "scalatest" % "3.2.11" % Test,
  "org.mockito" %% "mockito-scala" % "1.17.7" % Test

)
