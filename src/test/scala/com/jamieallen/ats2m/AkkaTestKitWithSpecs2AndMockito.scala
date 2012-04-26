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

      val errorKernel = _system.actorOf(Props(new ErrorKernel(externalService)))
      errorKernel ! StartWorkers
      Thread.sleep(3000)
      there was one(externalService).goDoSomething
    }
  }

  //  def is = {
  //    "This is a specification to check actor system side effects" ^
  //      "The external service should" ^
  //      "be eventually called" ! TestMocks().e1 ^
  //      end
  //
  //    case class TestMocks() extends Mockito {
  //      def externalService = mock[ExternalService]
  //      def e1 = there was one(externalService).goDoSomething
  //    }
  //  }

  // Need Specs2 BeforeAfter
  //  override def afterAll {
  //    system.shutdown()
  //  }

  //  val system = null
  //
  //  class InnerSpecification extends TestKit(system) {
  //    val mockOfSomething = mock[ExternalService]
  //
  //    def someMethodUsingAkkaTestKit(): Example {
  //      // do some things and also still enjoy the ability use Specs2 
  //      todo
  //    }
  //  }
  //
  //  "My Specification" should {
  //    "be able to pass a test involving Akka and Specs2" in {
  //      val spec = new InnerSpecification()
  //      spec.someMethodUsingAkkaTestKit()
  //    }
  //  }
}
