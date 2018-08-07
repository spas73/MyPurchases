package controllers

import javax.inject.Inject
import play.api.Configuration
import play.api.mvc._

/**
 * Main application controller.
 */
//class Application @Inject()(val configuration: Configuration) extends Controller {

class Application @Inject() (config: Configuration, c: ControllerComponents) extends AbstractController(c) {
  /**
   * Redirect to the product list.
   */
  def index = Action { implicit request =>
    Redirect(routes.Products.list())
  }
}
