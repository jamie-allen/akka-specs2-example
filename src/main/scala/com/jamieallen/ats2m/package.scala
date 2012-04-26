package com.jamieallen

package object ats2m {
  sealed trait ControlMessage
  case object StartWorkers extends ControlMessage
  case object StopWorkers extends ControlMessage

  sealed trait AB_Message
  case class AB(value: Int) extends AB_Message

  sealed trait AC_Message
  case class AC(value: Int) extends AC_Message

  sealed trait BA_Message
  case class BA(value: Int) extends BA_Message

  sealed trait BC_Message
  case class BC(value: Int) extends BC_Message

  sealed trait CA_Message
  case class CA(value: Int) extends CA_Message

  sealed trait CB_Message
  case class CB(value: Int) extends CB_Message
}