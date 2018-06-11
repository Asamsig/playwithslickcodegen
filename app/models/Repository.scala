package models

import models.Tables._
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

/**
  * A repository.
  *
  * @param dbConfigProvider The Play db config provider. Play will inject this for you.
  */
@Singleton
class Repository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  /**
    * List data from the database.
    */
  def list() = db.run {
    val query = EmployeesTable.take(100).result
    println(query.statements.mkString)
    query
  }
}
