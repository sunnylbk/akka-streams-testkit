name := "akka-streams-testkit"

organization := "com.ihealthtechnologies"

version := "2.0.0-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value % "compile",
  "com.typesafe.akka" %% "akka-stream-experimental" % "1.0-M4" % "compile",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.9" % "compile",
  "org.scalatest" %% "scalatest" % "2.2.3" % "compile"
)

scalacOptions ++= Seq("-feature") // Show feature warnings.

resolvers ++= Seq(
  "Ifi Public" at "https://maven.ifi.uzh.ch/maven2/content/groups/public/"
)

// Adds the resource folders to Eclipse projects.
EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

// Makes the source code of dependencies accessible in Eclipse projects. 
EclipseKeys.withSource := true

// Publish artifact to Artifactory
credentials += Credentials("Artifactory Realm", "ihealthtechnologies.artifactoryonline.com", System.getenv("ARTIFACTORY_USER"), System.getenv("ARTIFACTORY_PASSWORD"))

publishMavenStyle := true

publishTo := {
  val url = System.getenv("ARTIFACTORY_URL")
  if (isSnapshot.value)
  Some("snapshots" at url + "libs-snapshots-local")
    else
  Some("releases" at url + "libs-releases-local")
}
