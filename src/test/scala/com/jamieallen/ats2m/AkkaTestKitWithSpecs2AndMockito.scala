package com.jamieallen.ats2m

import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.testkit._
import akka.actor.Props
import akka.util.duration._

/**
 * Required to fix implicit collision with TestKit duration
 */
//trait DeactivatedTimeConversions extends org.specs2.time.TimeConversions {
//  override def intToRichLong(v: Int) = super.intToRichLong(v)
//  override def longToRichLong(v: Long) = super.longToRichLong(v)
//}

@RunWith(classOf[JUnitRunner])
class AkkaTestKitWithSpecs2AndMockito(_system: ActorSystem) extends TestKit(_system) with Specification with Mockito { //with DeactivatedTimeConversions {
  def this() = this(ActorSystem())

  "The actor system" should {
    "make a call into the ExternalService" in {
      def externalService = mock[ExternalService]
      externalService.goDoSomething returns "Hello"

      val a = system.actorOf(Props[A_Actor])
      val b = system.actorOf(Props(new B_Actor(a)))
      val c = system.actorOf(Props(new C_Actor(a, b, externalService)))

      println("***** STARTING B *****")
      b ! StartWorkers
      println("***** STARTING C *****")
      c ! StartWorkers
      Thread.sleep(100)
      there was atLeastOne(externalService).goDoSomething
    }
  }
}
