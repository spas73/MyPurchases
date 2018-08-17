package controllers

import javax.inject._
import play.api.i18n.{Lang, MessagesApi}
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class ChartController @Inject()(products: models.Products)(
    implicit ec: ExecutionContext,
    messagesApi: MessagesApi,
    c: ControllerComponents)
    extends AbstractController(c) {
  implicit val messages = messagesApi.preferred(Seq(Lang.defaultLang))
  def index = Action.async { implicit request =>
    {
      products.getProducts.map { data =>
        {
          val x: String = Json.stringify(Json.toJson(data))
          Ok(views.html.chart("My purchases", "All products", x))
        }
      }
    }
  }

}
