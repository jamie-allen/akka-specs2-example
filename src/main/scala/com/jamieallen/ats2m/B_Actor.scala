package com.jamieallen.ats2m

import akka.actor._
import com.weiglewilczek.slf4s.Logging

class B_Actor(a: ActorRef) extends Actor with Logging {
  var ab_count = 0
  var cb_count = 0

  logger.info("Started up")

  def receive = {
    case AB(x) => {
      logger.debug("Got AB message with value %d".format(x))
      ab_count += 1
      sender ! BA(ab_count)
    }
    case CB(y) => {
      logger.debug("Got CB message with value %d".format(y))
      cb_count += 1
      sender ! BC(cb_count)
    }
    case StartWorkers => {
      logger.debug("Starting B")
      a ! BA(cb_count)
    }
  }
}