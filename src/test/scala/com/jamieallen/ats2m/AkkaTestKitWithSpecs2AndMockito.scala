package com.jamieallen.ats2m

import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit._

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
      val externalService = mock[ExternalService].defaultReturn("Hello")

      val a = system.actorOf(Props[A_Actor])
      val b = system.actorOf(Props(new B_Actor(a)))
      val c = system.actorOf(Props(new C_Actor(a, b, externalService)))

      b ! StartWorkers
      c ! StartWorkers
      println("*** STARTED ACTORS ***")

      Thread.sleep(10)
      there was atLeastOne(externalService).goDoSomething
    }
  }
}
