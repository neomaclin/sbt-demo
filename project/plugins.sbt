//packaging
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")

//releasing
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.0.0")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.7")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.4.1")

//micro-site/documents
addSbtPlugin("com.47deg"  % "sbt-microsites" % "0.9.4")
addSbtPlugin("org.tpolecat" % "tut-plugin" % "0.6.12")

//Docker related
addSbtPlugin("se.marcuslonnberg" % "sbt-docker" % "1.5.0")
addSbtPlugin("com.tapad" % "sbt-docker-compose" % "1.0.34")

//code formatting
addSbtPlugin("com.lightbend.sbt" % "sbt-java-formatter" % "0.4.4")
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.0.4")

//others
addSbtPlugin("ch.jodersky" % "sbt-jni" % "1.3.4")
addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.3.7")

//Testing Framework - Java
resolvers += Resolver.jcenterRepo
addSbtPlugin("de.johoop" % "sbt-testng-plugin" % "3.1.1")
addSbtPlugin("net.aichler" % "sbt-jupiter-interface" % "0.8.2")