name := "scala-demo"

version := "1.0"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= typelevel ++ reactive ++ validation ++ auxiliary

lazy val typelevel = cats ++ shapeless ++ spire
lazy val reactive = akka ++ http
lazy val auxiliary = logs ++ enums ++ args

lazy val cats = {
   val catsVersion  = "1.6.0"
   Seq("org.typelevel" %% "cats-effect" % "1.3.1", "org.typelevel" %% "kittens" % "1.1.0") ++
   Seq("org.typelevel" %% "cats-core", "org.typelevel" %% "cats-free").map(_ % catsVersion)
}

lazy val spire = {
  Seq("org.typelevel" %% "spire" % "0.16.2")
}
lazy val shapeless = {
  Seq(
    "com.chuusai" %% "shapeless" % "2.3.3",
      "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.3"
  )
}

lazy val validation = {
  Seq(
    "org.scalacheck" %% "scalacheck"                  % "1.13.4",
    "org.scalatest"  %% "scalatest"                   % "3.0.1",
    "org.scalamock"  %% "scalamock-scalatest-support" % "3.5.0"
  ).map(_ % Test)
}

lazy val akka = {
  val akkaVersion = "2.5.22"
  Seq(
    "com.typesafe.akka"   %% "akka-actor"              % akkaVersion,
    "com.typesafe.akka"   %% "akka-stream"             % akkaVersion,
    "com.typesafe.akka"   %% "akka-cluster-sharding"   % akkaVersion,
    "com.typesafe.akka"   %% "akka-persistence"        % akkaVersion excludeAll (ExclusionRule("io.netty")),
    "com.typesafe.akka"   %% "akka-persistence-query"  % akkaVersion,
    "com.typesafe.akka"   %% "akka-distributed-data"   % akkaVersion,
    "com.typesafe.akka"   %% "akka-multi-node-testkit" % akkaVersion,
    "com.typesafe.akka"   %% "akka-testkit"            % akkaVersion % Test,
    "com.typesafe.akka"   %% "akka-stream-testkit"     % akkaVersion % Test
  )
}

lazy val http = {
  val akkaHttpVersion = "10.1.8"
  Seq(
    "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion,
    "de.heikoseeberger" %% "akka-http-circe"      % "1.21.0"
  )
}

lazy val logs = {
  Seq(
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
  )
}

lazy val args = {
  val version = "3.5.1"
  Seq(
    "org.backuity.clist" %% "clist-core"  % version,
    "org.backuity.clist" %% "clist-macros" % version % "provided"
  )
}

lazy val enums = {
  Seq(
    "com.beachape" %% "enumeratum" % "1.5.13",
    "com.beachape" %% "enumeratum-cats" % "1.5.15"
  )
}
