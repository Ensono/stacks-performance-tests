package com.amido.scenarios.menu

import com.amido.requests.menu.CreateMenuRequest
import com.amido.requests.menu.CreateMenuRequest.randomName
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object CreateMenuScenario {

  val createMenu: ScenarioBuilder = scenario("CREATE menu SUCCESS scenario")
    .feed(randomName)
    .exec(CreateMenuRequest.createMenu)

  val createMenuError: ScenarioBuilder = scenario("CREATE menu error BAD REQUEST scenario")
    .exec(CreateMenuRequest.createMenuError)
}
