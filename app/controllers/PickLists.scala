package controllers

import java.util.Date

import javax.inject.Inject
import models.PickList
import play.api._
import play.api.i18n.{Lang, MessagesApi}
import play.api.mvc._
import play.twirl.api.Html

import scala.concurrent.Future

class PickLists @Inject()(config: Configuration,
                          c: ControllerComponents,
                          messagesApi: MessagesApi)
    extends AbstractController(c) {
  implicit val messages = messagesApi.preferred(Seq(Lang.defaultLang))

  /**
    * Renders a pick list synchronously in the usual way.
    */
  def preview(warehouse: String) = Action { implicit request =>
    val pickList = PickList.find(warehouse)
    val timestamp = new java.util.Date
    Ok(views.html.pickList(warehouse, pickList, timestamp))
  }

  /**
    * Starts a job to generate a pick list.
    */
  def sendAsync(warehouse: String) = Action { implicit request =>
    import scala.concurrent.ExecutionContext.Implicits.global
    Future {
      val pickList = PickList.find(warehouse)
      send(views.html.pickList(warehouse, pickList, new Date))
    }
    Redirect(routes.PickLists.index())
  }

  /**
    * Renders the home page.
    */
  def index = Action { implicit request =>
    Ok(views.html.sendPickLists())
  }

  /**
    * Stub for ‘sending’ the pick list as an HTML document, e.g. by e-mail.
    */
  private def send(html: Html) {
    Logger.info(html.body)
    // Send pick list…
  }

}
