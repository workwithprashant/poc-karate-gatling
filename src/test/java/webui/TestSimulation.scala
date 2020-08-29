package webui

import com.intuit.karate.gatling.KarateProtocol
import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration.DurationInt

class TestSimulation extends Simulation {
  val usersRamp = 5;
  val runDuration = 10
  val protocol: KarateProtocol = karateProtocol("/*" -> pauseFor("*" -> 10))

  val webuiGithub: ScenarioBuilder = scenario("Testing GitHub").exec(karateFeature("webui/github.feature"))
  val webuiGoogle: ScenarioBuilder = scenario("Testing Google").exec(karateFeature("webui/google.feature"))

  setUp(
    webuiGithub.inject(rampUsers(usersRamp).during(runDuration seconds)),
    webuiGoogle.inject(rampUsers(usersRamp).during(runDuration seconds))
  ).protocols(protocol)
  //    .maxDuration(runDuration seconds)
  //    .assertions(
  //      global.responseTime.max.between(0, 5000),
  //      global.failedRequests.percent.is(0),
  //      global.requestsPerSec.gte(targetReqPerSec),
  //      global.successfulRequests.percent.gt(90)
  //    )
}