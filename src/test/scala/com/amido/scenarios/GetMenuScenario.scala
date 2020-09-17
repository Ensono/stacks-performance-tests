package com.amido.scenarios

import com.amido.requests.menu.CreateMenuRequest.randomName
import com.amido.requests.menu.{CreateMenuRequest, GetMenuRequest}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

//Scenarios are the business scenarios that will be run in the load tests. Scenarios execute requests.
//Docs: https://gatling.io/docs/current/general/scenario
object GetMenuScenario {
  val getMenuCollection: ScenarioBuilder = scenario("GET menu collection scenario")
    .exec(GetMenuRequest.getMenuCollection)

  val getMenuResource: ScenarioBuilder = scenario("CREATE then GET menu resource scenario")
    .feed(randomName)
    .exec(CreateMenuRequest.createMenu)
    .pause(1,2)
    .exec(GetMenuRequest.getMenu)
}
