package controllers

import javax.inject.{Inject, Singleton}
import play.api.http.ContentTypes
import play.api.i18n.{Lang, MessagesApi}
import play.api.libs.EventSource
import play.api.mvc._

@Singleton
class ScalaEventSourceController @Inject()(cc: ControllerComponents,
                                           messagesApi: MessagesApi)
    extends AbstractController(cc)
    with ScalaTicker
    with ScalaAlertNewProducts {

  implicit val messages = messagesApi.preferred(Seq(Lang.defaultLang))

  def index() = Action { implicit request =>
    Ok(views.html.scalaeventsource())
  }

  def streamClock() = Action {
    Ok.chunked(stringSource via EventSource.flow).as(ContentTypes.EVENT_STREAM)
  }

  def streamAlertNewProducts() = Action {
    Ok.chunked(stringAlertNewProducts via EventSource.flow).as(ContentTypes.EVENT_STREAM)
  }


}
