package com.jamieallen.ats2m

import akka.actor.Actor
import com.weiglewilczek.slf4s.Logging
import akka.actor.Props
import akka.actor.PoisonPill

class ErrorKernel(externalService: ExternalService) extends Actor with Logging {
  import context.system

  val a = system.actorOf(Props[A_Actor])
  val b = system.actorOf(Props(new B_Actor(a)))
  val c = system.actorOf(Props(new C_Actor(a, b, externalService)))

  def receive = {
    case StartWorkers =>
      b ! StartWorkers
      c ! StartWorkers
    case StopWorkers =>
      a ! PoisonPill
      b ! PoisonPill
      c ! PoisonPill
  }
}