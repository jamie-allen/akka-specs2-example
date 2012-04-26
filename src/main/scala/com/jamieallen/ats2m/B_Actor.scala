package com.jamieallen.ats2m

import akka.actor._

class B_Actor(a: ActorRef) extends Actor {
  var ab_count = 0
  var cb_count = 0

  def receive = {
    case AB(x) =>
      // println("Got AB message with value %d".format(x))
      ab_count += 1
      sender ! BA(ab_count)
    case CB(y) =>
      // println("Got CB message with value %d".format(y))
      cb_count += 1
      sender ! BC(cb_count)
    case StartWorkers =>
      a ! BA(cb_count)
  }
}