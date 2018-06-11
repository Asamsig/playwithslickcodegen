package controllers

import javax.inject._

import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class Controller @Inject()(repo: Repository)(
    implicit ec: ExecutionContext
) extends InjectedController {

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
      Ok(Json.toJson(data))
    }
  }
}