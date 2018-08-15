package controllers


import akka.actor.Cancellable
import akka.stream.scaladsl.Source
import models.Product

import scala.concurrent.duration._

trait ScalaAlertNewProducts {

  def stringAlertNewProducts: Source[String, _] = {


    val tickSource: Source[String, Cancellable] = Source.tick(0 millis, 100 millis, "TICK")
    val s: Source[String, Cancellable] = tickSource.map((tick) => {

      var msg: String = "no new product"
      if (Product.newProductsCounter > 0) {
        msg = "there are new products. Please update the page"
      }
      msg

    })
    s


  }


}
