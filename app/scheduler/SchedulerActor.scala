package scheduler

import akka.actor.Actor
import cuzfrog.maila.Maila
import javax.inject.{Inject, Singleton}

import scala.concurrent.ExecutionContext

@Singleton
class SchedulerActor @Inject()()(implicit ec: ExecutionContext) extends Actor {
  override def receive: Receive = {
    case _ =>
      // your job here
      println("read mails...")

      val maila = Maila.newInstance(askUser = "mailservice1973@gmail.com",
                                    askPassword = "tkniommdyolzowgm")
      val mails = maila.read() //get a List of mails
      mails.foreach(m => {
        println(m.subject)
        println(m.contentText)
        println(
          "--------------------------------------------------------------")
      }) //print text content

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
