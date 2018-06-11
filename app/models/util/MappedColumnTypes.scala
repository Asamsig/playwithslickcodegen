package models.util

import java.time.LocalDate

import java.sql.Date

/**
  * Created by Alexander on 11/06/2018
  */
trait MappedColumnTypes {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._

  implicit val localDateTimeToSqlDate = MappedColumnType.base[LocalDate, Date](Date.valueOf, _.toLocalDate)

}
