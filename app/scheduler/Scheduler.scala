package scheduler

import akka.actor.{ActorRef, ActorSystem}
import javax.inject.{Inject, Named}
import play.api.Configuration

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class Scheduler @Inject()(
    val system: ActorSystem,
    @Named("scheduler-actor") val schedulerActor: ActorRef,
    configuration: Configuration)(implicit ec: ExecutionContext) {
  val frequency: Int = configuration.get[Int]("frequency")
  var actor = system.scheduler.schedule(0.microseconds,
                                        frequency.seconds,
                                        schedulerActor,
                                        "update")

}
