import xerial.sbt.Sonatype.autoImport._
import ReleaseTransformations._

name := baseDirectory.value.getName

version := "1.0.0"

organization := "co.pragmati"

scalaVersion := "2.12.2"

crossScalaVersions := Seq("2.11.8", "2.12.0")

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {

  val akkaV       = "10.0.+"

	Seq (
		"com.typesafe.akka" %% "akka-http" % akkaV % "provided"
	)
}

homepage := Some(url("https://github.com/pragmatico/swagger-ui-akka-http"))

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

releasePublishArtifactsAction := PgpKeys.publishSigned.value

releaseProcess := Seq[ReleaseStep](
	checkSnapshotDependencies,
	inquireVersions,
	runClean,
	runTest,
	setReleaseVersion,
	commitReleaseVersion,
	tagRelease,
	ReleaseStep(action = Command.process("publishSigned", _)),
	setNextVersion,
	commitNextVersion,
	ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
	pushChanges
)

//sonatypeProfileName := "co.pragmati"

// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
			<scm>
				<connection>scm:git:git@github.com:pragmatico/swagger-ui-akka-http.git</connection>
				<developerConnection>scm:git:git@github.com:pragmatico/swagger-ui-akka-http.git</developerConnection>
				<url>github.com/pragmatico</url>
			</scm>
			<developers>
				<developer>
					<id>jmbataller</id>
					<name>Jose Miguel Bataller</name>
					<url>http://github.com/jmbataller</url>
				</developer>
			</developers>
}
