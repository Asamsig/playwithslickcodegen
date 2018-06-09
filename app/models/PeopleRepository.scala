package models

import models.Tables._
import javax.inject.{Inject, Singleton}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.Future

/**
  * A repository for people.
  *
  * @param dbConfigProvider The Play db config provider. Play will inject this for you.
  */
@Singleton
class PeopleRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
    extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  /**
    * Create a person with the given name and age.
    *
    * This is an asynchronous operation, it will return a future of the created people, which can be used to obtain the
    * id for that people.
    */
  def create(name: String, age: Int): Future[People] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (PeopleTable.map(p => (p.name, p.age))
    // Now define it to return the id, because we want to know what id was generated for the people
      returning PeopleTable.map(_.id)
    // And we define a transformation for the returned value, which combines our original parameters with the
    // returned id
      into ((nameAge, id) => People(id, nameAge._1, nameAge._2))
    // And finally, insert the person into the database
    ) += (name, age)
  }

  /**
    * List all the people in the database.
    */
  def list(): Future[Seq[People]] = db.run {
    PeopleTable.result
  }
}
