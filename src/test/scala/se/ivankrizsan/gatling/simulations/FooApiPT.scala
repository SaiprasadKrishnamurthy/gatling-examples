package se.ivankrizsan.gatling.simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class FooApiPT extends Simulation {
  private val baseUrl = "https://jsonplaceholder.typicode.com"
  private val basicAuthHeader = "Basic YmxhemU6UTF3MmUzcjQ="
  private val authPass = "Q1w2e3r4"
  private val contentType = "application/json"
  private val endpoint = "/posts/1"
  private val authUser = "blaze"
  private val requestCount = 10

  /* Place for arbitrary Scala code that is to be executed before the simulation begins. */
  before {
    println("***** My simulation is about to begin! *****")
  }

  /* Place for arbitrary Scala code that is to be executed after the simulation has ended. */
  after {
    println("***** My simulation has ended! ******")
  }

  val httpProtocol: HttpProtocolBuilder = http
    .baseURL(baseUrl)
    .inferHtmlResources()
    .acceptHeader("*/*")
    //.authorizationHeader(basicAuthHeader)
    .contentTypeHeader(contentType)
    .userAgentHeader("curl/7.54.0")

  val headers_0 = Map("Expect" -> "100-continue")

  val scn: ScenarioBuilder = scenario("RecordedSimulation")
    .exec(http("FooEndpoint1")
      .get(endpoint)
      .headers(headers_0)
      //.basicAuth(authUser, authPass)
      .check(status.is(200)))
    .exec(http("FooEndpoint2")
      .get(endpoint)
      .headers(headers_0)
      //.basicAuth(authUser, authPass)
      .check(status.is(200)))
    .exec(http("FooEndpoint3")
      .get(endpoint)
      .headers(headers_0)
      //.basicAuth(authUser, authPass)
      .check(status.is(200)))


  setUp(scn.inject(atOnceUsers(requestCount))).protocols(httpProtocol)
}
