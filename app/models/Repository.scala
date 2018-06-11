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
    EmployeesTable.join(SalariesTable).on(_.empNo === _.empNo).take(100).result
  }
}
