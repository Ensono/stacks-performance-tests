package com.amido.scenarios

import com.amido.requests.CreateMenuRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder

object CreateMenuScenario {
  val createMenuScenario: ScenarioBuilder = scenario("Get menu scenario")
    .exec(CreateMenuRequest.create_menu)
}
