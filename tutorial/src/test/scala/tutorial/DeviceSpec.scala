package tutorial

import akka.actor.{ ActorSystem }
import akka.testkit.{ TestKit, TestProbe }

import org.scalatest.{ BeforeAndAfterAll, Matchers, WordSpecLike }
import scala.concurrent.duration._

class DeviceSpec
extends TestKit(ActorSystem("part3system"))
with WordSpecLike
with Matchers
with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Device actor" must {

    "reply with empty reading if no temperature is known" in {
      val probe = TestProbe()
      val deviceActor = system.actorOf(Device.props("group", "device"))

      deviceActor.tell(Device.ReadTemperature(requestId = 42), probe.ref)
      val response = probe.expectMsgType[Device.RespondTemperature]
      response.requestId should ===(42)
      response.value should ===(None)
    }
  }
}
