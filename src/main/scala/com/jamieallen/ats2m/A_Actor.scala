package com.jamieallen.ats2m

import akka.actor._
import com.weiglewilczek.slf4s.Logging

class A_Actor extends Actor with Logging {
  var ba_count = 0
  var ca_count = 0

  logger.info("Started up")

  def receive = {
    case BA(x) => {
      logger.debug("Got BA message with value %d".format(x))
      ba_count += 1
      sender ! AB(ba_count)
    }
    case CA(y) => {
      logger.debug("Got CA message with value %d".format(y))
      ca_count += 1
      sender ! AC(ca_count)
    }
  }
}