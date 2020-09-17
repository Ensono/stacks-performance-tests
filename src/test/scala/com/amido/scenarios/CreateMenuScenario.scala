package com.amido.scenarios

import com.amido.requests.menu.CreateMenuRequest
import com.amido.requests.menu.CreateMenuRequest.randomName
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object CreateMenuScenario {

  val createMenu: ScenarioBuilder = scenario("CREATE menu scenario")
    .feed(randomName)
    .exec(CreateMenuRequest.createMenu)

  val createMenuError: ScenarioBuilder = scenario("CREATE menu error 400 scenario")
    .exec(CreateMenuRequest.createMenuError)
}
