// The Play plugin
addSbtPlugin("com.typesafe.play"    % "sbt-plugin"        % "2.6.15")
addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.3.0")
addSbtPlugin("com.thesamet"         % "sbt-protoc"        % "0.99.18")

libraryDependencies ++= Seq(
  "com.thesamet.scalapb" %% "compilerplugin"      % "0.7.4",
  "mysql"                % "mysql-connector-java" % "5.1.46"
)
