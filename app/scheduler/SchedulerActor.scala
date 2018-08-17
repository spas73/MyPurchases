package scheduler

import akka.actor.Actor
import cuzfrog.maila.Maila
import javax.inject.{Inject, Singleton}
import models.Product
import org.jsoup.Jsoup
import org.jsoup.nodes.{Attribute, Document, Element}
import org.jsoup.select.Elements

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext
@Singleton
class SchedulerActor @Inject()()(implicit ec: ExecutionContext) extends Actor {

  def getBarcode(htmlString: String): String = {

    val document: Document = Jsoup.parse(htmlString)
    val elements: Elements = document.body.select("div")
    for (element: Element <- elements.asScala) {
      element.attributes.asScala.foreach { attribute: Attribute =>
        if (attribute.getKey == "dir" && attribute.getValue == "auto") {
          if (element.hasText) return (element.text())
        }
      }
    }
    ""
  }

  private def isAllDigits(x: String): Boolean =
    (x != "") && (x forall Character.isDigit)

  override def receive: Receive = {
    case _ =>
      // your job here
      println("read mails...")

      val maila = Maila.newInstance(askUser = "mailservice1973@gmail.com",
                                    askPassword = "tkniommdyolzowgm")
      val mails = maila.read() //get a List of mails
      mails.foreach(m => {
        val barcode: String = getBarcode(m.contentText)
        println("barcode received = " + barcode)

        if (isAllDigits(barcode)) {
          val product: Option[Product] = Product.findByEan(barcode.toLong)
          if (!product.isDefined) {
            Product.add(Product(barcode.toLong, "unknown name", "unknown description", 0))
            Product.newProductsCounter += 1
          }

        } else { println("barcode rejected") }

      })

  }

}
//reading mail:
//import com.github.cuzfrog.maila.{MailFilter, Maila}
//
//val maila = Maila.newInstance(askUser = "user0@some.com" ,askPassword = "pw")
//val mails1 = maila.read() //get a List of mails using default filter.
//
//val filter = MailFilter(
//maxSearchAmount = 30,
//filter = _.subject.contains("myKeyWord")
//)
//val mails2 = maila.read(filter) //get a List of mails
//mails2.foreach(m => println(m.contentText)) //print text content
//
//sending mail:
//import com.github.cuzfrog.maila.{Mail, Maila}
//val maila = Maila.newInstance(askUser = "user0@some.com" ,askPassword = "pw")
//val mail = Mail(List("recipient@google.com"), "subject", "text content")
//maila.send(List(mail))
//maila.send(List(mail),isParallel =  true) //sending every mail in Future.

object Test_Barcode extends App {
  val htmlString =
    "<div>\n 1922IT \n <div dir=\"auto\">\n  1922IT\n </div> \n</div>"
  val document: Document = Jsoup.parse(htmlString)
  val elements: Elements = document.body.select("div")
  for (element: Element <- elements.asScala) {
    element.attributes.asScala.foreach { attribute: Attribute =>
      if (attribute.getKey == "dir" && attribute.getValue == "auto") {
        if (element.hasText) println(element.text())
      }
    }
  }
}
