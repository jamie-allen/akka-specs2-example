package com.jamieallen.ats2m

import akka.actor._

class A_Actor extends Actor {
  var ba_count = 0
  var ca_count = 0

  def receive = {
    case BA(x) =>
      // println("Got BA message with value %d".format(x))
      ba_count += 1
      sender ! AB(ba_count)
    case CA(y) =>
      // println("Got CA message with value %d".format(y))
      ca_count += 1
      sender ! AC(ca_count)
    case Blah(z) if z == 0 => throw new IllegalArgumentException("Got invalid data")
    case Blah(z) => println("**** HERE ****"); sender ! Blah(z)
  }
}