package com.jamieallen.ats2m

import akka.actor._

class C_Actor(a: ActorRef, b: ActorRef, proxy: ExternalService) extends Actor {
  val CountThreshold = 100

  var ac_count = 0
  var bc_count = 0

  def receive = {
    case AC(x) =>
      // println("Got AC message with value %d".format(x))
      ac_count += 1
      checkState
      a ! CA(ac_count)
    case BC(y) =>
      // println("Got BC message with value %d".format(y))
      bc_count += 1
      checkState
      b ! CB(bc_count)
    case StartWorkers =>
      a ! CA(bc_count)
      b ! CB(ac_count)
  }

  // Note the final modifier - this is here for performance reasons
  final def checkState() =
    if (ac_count > CountThreshold && bc_count > CountThreshold)
      println("*** Calling the proxy, value: %s ***".format(proxy.goDoSomething))
}