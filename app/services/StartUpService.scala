package services

import javax.inject._
import play.api.Configuration
import play.api.inject._

import scala.concurrent._

class StartUpService @Inject()(configuration: Configuration,
                               lifecycle: ApplicationLifecycle) {
  lifecycle.addStopHook(() =>
    Future.successful {
      println("Goodbye")
  })
  println("Hello")

//  val host: String = "smtp.gmail.com"
//  val port: Int = 465
//  val user: String = "silviomariopastori@gmail.com"
//  val password: String = "@1q2w3e4r5t6y1973@"
//  val inboxName: String = "inbox"
//
//
//
//  val system = akka.actor.ActorSystem("system")
//  val mailActor = system.actorOf(
//    MailReceiverActor.props(host, port, user, password, inboxName))
//
//  implicit val executionContext = // user defined or use the default play context
//    system.scheduler.schedule(5.seconds,
//                              20.minutes,
//                              mailActor,
//                              MailReceiverActor.Check)

}


