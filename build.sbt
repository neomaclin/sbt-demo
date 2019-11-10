name := "sbt-demo"

version := "1.0"

scalaVersion := "2.13.1"

enablePlugins(JmhPlugin)
enablePlugins(TestNGPlugin)
javacOptions ++= Seq("-source", "11", "-target", "11")
compileOrder := CompileOrder.JavaThenScala
parallelExecution in Test := false

libraryDependencies ++= typelevel ++ reactive ++ validation ++ auxiliary

lazy val typelevel = cats ++ shapeless ++ monix
lazy val reactive = akka ++ http ++ circe
lazy val auxiliary = logs ++ enums ++ args ++ validation

lazy val cats = {
  Seq(
    "org.typelevel" %% "kittens",
    "org.typelevel" %% "cats-effect",
    "org.typelevel" %% "cats-core",
    "org.typelevel" %% "cats-free"
  ).map(_ % "2.0.0")
}


lazy val shapeless = {
  Seq(
    "com.chuusai" %% "shapeless" % "2.3.3",
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.3"
  )
}

lazy val monix = {
  Seq("io.monix" %% "monix" % "3.0.0")
}

lazy val validation = {
  Seq(
    "org.scalacheck" %% "scalacheck" % "1.14.1",
    "org.scalatest" %% "scalatest" % "3.0.8",
    "org.scalamock" %% "scalamock" % "4.4.0",
    "com.storm-enroute" %% "scalameter" % "0.19",
    "org.testng" % "testng" % "7.0.0",
    "net.aichler" % "jupiter-interface" % "0.8.2",
    "org.junit.jupiter" % "junit-jupiter-engine" % "5.5.2"
  ).map(_ % Test)
}


lazy val akka = {
  val akkaVersion = "2.6.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-metrics" % akkaVersion,
    "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
    "com.typesafe.akka" %% "akka-persistence" % akkaVersion excludeAll (ExclusionRule(
      "io.netty"
    )),
    "com.typesafe.akka" %% "akka-persistence-query" % akkaVersion,
    "com.typesafe.akka" %% "akka-distributed-data" % akkaVersion,
    "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test
  )
}


lazy val http = {
  val akkaHttpVersion = "10.1.10"
  Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
    "de.heikoseeberger" %% "akka-http-circe" % "1.28.0"
  )
}

lazy val logs = {
  Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
  )
}

lazy val args = {
  val version = "3.5.1"
  Seq(
    "org.backuity.clist" %% "clist-core" % version,
    "org.backuity.clist" %% "clist-macros" % version % "provided"
  )
}

lazy val enums = {
  Seq(
    "com.beachape" %% "enumeratum" % "1.5.13",
    "com.beachape" %% "enumeratum-circe" % "1.5.22",
    "com.beachape" %% "enumeratum-cats" % "1.5.16"
  )
}


lazy val circe = {
  val circeVersion = "0.12.3"
  Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion)
}
