package com.amido.scenarios

import com.amido.requests.GetMenuRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder

//Scenarios are the business scenarios that will be run in the load tests. Scenarios execute requests.
//Docs: https://gatling.io/docs/current/general/scenario
object GetMenuScenario {
  val getMenuScenario: ScenarioBuilder = scenario("Get menu scenario")
    .exec(GetMenuRequest.get_menu)
}
