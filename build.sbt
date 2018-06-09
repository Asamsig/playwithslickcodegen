enablePlugins(PlayScala, CodegenPlugin)
name := "playwithslickdemo"

version := "1.0"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-slick" % "3.0.3",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.3",
  "mysql"             % "mysql-connector-java" % "5.1.46",
  specs2              % Test
)

dependencyOverrides += "com.typesafe.slick" %% "slick" % "3.2.3"

// Register codegen hook
sourceGenerators in Compile += slickCodegen.taskValue
slickCodegenDatabaseUrl := "jdbc:mysql://localhost:3306/demo?verifyServerCertificate=false&useSSL=false"
slickCodegenDatabaseUser := "root"
slickCodegenDatabasePassword := ""
slickCodegenDriver := slick.jdbc.MySQLProfile
slickCodegenJdbcDriver := "com.mysql.jdbc.Driver"
slickCodegenOutputPackage := "models"
slickCodegenCodeGenerator := { new MyCustomCodegen(_) }
slickCodegenExcludedTables := Seq("play_evolutions")
