package com.amido.scenarios.menu


import com.amido.requests.menu.CreateMenuRequest.randomName
import com.amido.requests.menu.UpdateMenuRequest
import com.amido.requests.menu.UpdateMenuRequest.randomNameUpdate
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object UpdateMenuScenario {

  val updateMenu: ScenarioBuilder = scenario("CREATE then UPDATE menu scenario")
    .feed(randomName)
    .exec(CreateMenuScenario.createMenu)
    .feed(randomNameUpdate)
    .exec(UpdateMenuRequest.updateMenu)

  val updateMenuError: ScenarioBuilder = scenario("CREATE then UPDATE menu error scenario")
    .feed(randomName)
    .exec(CreateMenuScenario.createMenu)
    .exec(UpdateMenuRequest.updateMenuError)

  val updateMenuResourceNotFound: ScenarioBuilder = scenario("UPDATE menu resource NOT FOUND scenario")
    .exec(UpdateMenuRequest.updateMenuResourceNotFound)
}
