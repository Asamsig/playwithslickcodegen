package controllers

import javax.inject._
import models._
import models.demo.Employee
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

class Controller @Inject()(repo: Repository)(implicit ec: ExecutionContext) extends InjectedController {

  /**
    * The index action.
    */
  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  /**
    * A REST endpoint that gets all the data as JSON.
    */
  def get = Action.async { implicit request =>
    repo.list().map { data =>
      val employeesAsProtoString = data.map(
        employee =>
          employee ->
            Json.obj("proto" -> Employee(
            employee.empNo,
            employee.firstName,
            employee.lastName,
            Employee.Gender.fromName(employee.gender).get,
            employee.birthDate.toString,
            employee.hireDate.toString
        ).toByteString.toStringUtf8)
      )
      Ok(Json.toJson(employeesAsProtoString))
    }
  }
}
