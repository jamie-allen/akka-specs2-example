scalaVersion := "2.9.1-1"

name := "Akka TestKit Specs2 Mockito Example"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-optimize")

libraryDependencies ++= Seq(
   "com.typesafe.akka" % "akka-actor" % "2.0",
   "com.weiglewilczek.slf4s" % "slf4s_2.9.1" % "1.0.7",
   "org.slf4j" % "slf4j-log4j12" % "1.6.1",
   "com.typesafe.akka" % "akka-testkit" % "2.0" % "test",
   "org.specs2" %% "specs2" % "1.9" % "test",
   "org.mockito" % "mockito-all" % "1.9.0" % "test",
   "junit" % "junit" % "4.7" % "test")
