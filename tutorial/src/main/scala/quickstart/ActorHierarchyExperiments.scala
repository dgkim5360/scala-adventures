package quickstart

import akka.actor.{ Props, ActorSystem }
import scala.io.StdIn

object ActorHierarchyExperiment1 extends App {
  val system = ActorSystem("testSystem")

  val firstRef = system.actorOf(Props[PrintMyActorRefActor], "first-actor")
  println(s"First: $firstRef")
  firstRef ! "printit"

  println(">>> Press ENTER to exit <<<")
  try StdIn.readLine()
  finally system.terminate()
}

object ActorHierarchyExperiment2 extends App {
  val system = ActorSystem("testSystem")
  val first = system.actorOf(Props[StartStopActor1], "first")
  first ! "stop"

  println(">>> Press ENTER to exit <<<")
  try StdIn.readLine()
  finally system.terminate()
}
