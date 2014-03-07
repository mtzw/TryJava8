name := "TryJava8"

version := "1.0-SNAPSHOT"

resolvers += "Local Maven Repository" at "file:///d:/m2repo"

libraryDependencies ++= Seq(
  "org.twitter4j" % "twitter4j-stream" % "3.0.5",
  "org.mockito" % "mockito-all" % "1.9.5",
  "com.novocode" % "junit-interface" % "0.10" % "test->default"
)

javacOptions ++= Seq(
  "-source", "1.8",
  "-encoding", "UTF-8"
)
