package com.amido.scenarios

import com.amido.requests.menu.CreateMenuRequest.randomName
import com.amido.requests.menu.UpdateMenuRequest
import com.amido.requests.menu.UpdateMenuRequest._
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object UpdateMenuScenario {

  val updateMenu : ScenarioBuilder = scenario("UPDATE menu scenario")
    .feed(randomName)
    .exec(CreateMenuScenario.createMenu)
    .feed(randomNameUpdate)
    .exec(UpdateMenuRequest.updateMenu)

}
