package com.jamieallen.ats2m

import akka.actor._
import com.weiglewilczek.slf4s.Logging

class C_Actor(a: ActorRef, b: ActorRef, proxy: ExternalService) extends Actor with Logging {
  val CountThreshold = 10000

  var ac_count = 0
  var bc_count = 0

  def receive = {
    case AC(x) =>
      logger.debug("Got AC message with value %d".format(x))
      ac_count += 1
      checkState
      b ! CA(ac_count)
    case BC(y) =>
      logger.debug("Got BC message with value %d".format(y))
      bc_count += 1
      checkState
      a ! CB(bc_count)
    case StartWorkers =>
      logger.debug("Starting C")
      a ! CA(bc_count)
      b ! CB(ac_count)
  }

  // Note the final modifier - this is here for performance reasons
  final def checkState() =
    if (ac_count > CountThreshold && bc_count > CountThreshold) {
      logger.info("Calling the proxy!")
      proxy.goDoSomething
    }
}