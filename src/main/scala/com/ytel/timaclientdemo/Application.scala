package com.ytel.timaclientdemo

import com.ytel.miefus.{MiefusApplication, TimaUserCacheConnection}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.Success
/**
  * Start writing your message handler..
  */
object Application extends MiefusApplication  {

  TimaCaccheTest.runSingle
  TimaCaccheTest.runLoad
  //Start test for ever
  TimaCaccheTest.repeatingTests
}

object TimaCaccheTest {

  def runSingle = TimaUserCacheConnection.getUser("55385270-563e-a9a1-069e-1dbca3f4eab2").onComplete {
    case Success(Right(x)) =>
      //This is user from redis.
      val user = x
      println(s"This is a account with sid: ${user.account_sid}, and is it active: ${user.active}")
    case _ => println("No User found")
  }

  def runLoad = for(i <- 1 to 1000) sids.foreach(x =>
    blocking {TimaUserCacheConnection.getUser(x).onComplete(println)}
  )

  val sids = List("4f8ee738-cd8d-6df7-ea5b-198ba11de4d7",
    "f7510c90-2a60-c5bd-4b9d-af70ba5380a7",
    "945285b3-268c-0f3a-a505-c7ea808ebd5e",
    "8259ee4a-e8fc-cf02-6eab-710780d8626f",
    "e898a433-4fe2-f2b5-f111-12f622df5218",
    "f6d03cf5-82b4-dc40-b46b-4b56e0f4fa2b",
    "70d97886-3416-b078-376f-1cf5d3a80947",
    "326dcc42-3268-e0b0-09dc-df52a5b00ec1",
    "32dc19d9-a9e6-8938-d2d3-3c7706f08299",
    "0b3b5d40-11da-58b7-aeb1-9ed119c73e6b",
    "4a80ec1d-4f7e-0c15-c557-86379643b4ec",
    "b1bdd51c-3e9f-bc88-cad3-21492eea8d48",
    "e2975783-f21a-1564-8e65-bbd7ce68afc7",
    "dd75ad59-84cc-9124-3752-7573dc821e54",
    "93552349-a292-2c79-c83f-6a4dc9fe8544",
    "b4b89488-18c7-ad1b-7ac2-280c337b00f9",
    "adcc8605-e767-98a2-6eb6-a57a331c89d7",
    "5549bd88-2efb-d1b1-ad5e-1838162cd4d8",
    "0625aa07-d23e-8461-f019-b954cef7c261",
    "8e4a1fb5-538a-4383-932f-686500774261",
    "a7100bf0-a1cc-6726-03da-30c24b2b62f9",
    "2ced47a1-d686-406d-a2fe-f8f0e0575e9f",
    "dbc95eab-1d8a-0109-f72b-fb11667bc247",
    "ec764c4a-200e-306c-9180-ba6557e83170",
    "6b957502-793f-0cc0-ffb6-443c0b953d67",
    "54e2cd0e-cf22-52da-ae17-487297e0c31e",
    "c262408c-9c0b-9229-007c-091ef0aa7a9a",
    "f6755d29-bc03-4a0e-496f-7ea5801e44cb",
    "403e9d54-109a-43e9-901d-b77eaee09572",
    "d2232988-9f45-6fa2-f051-f4ccb170f252",
    "062cc86b-e008-bd59-61ce-402728c33d78",
    "6dcd71a8-b1fd-b403-854c-589e440651ad",
    "a62d987a-4410-0b5b-fd83-44f9f5ec07ac",
    "fba52326-d440-c31a-bf22-388ea5966007",
    "6b56a9d3-33fb-55a7-d11a-5013c79b7edb",
    "55385270-563e-a9a1-069e-1dbca3f4eab2",
    "7ae5a9ae-b43d-38a6-d6a4-4ef67c0d2c41",
    "a9e90cee-8d4a-6fb5-b521-0f85e81cf1c4",
    "e4a2990d-dd4b-4cc5-b36d-f55f98c1cb70",
    "ee925960-1ce4-97a1-ce4e-181658dea84a")

  def repeatingTests: Unit = {
    runSingle
    runLoad
    Thread.sleep(3000)
    repeatingTests
  }
}

