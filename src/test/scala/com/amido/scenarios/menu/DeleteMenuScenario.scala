package com.amido.scenarios.menu

import com.amido.requests.menu.CreateMenuRequest.randomName
import com.amido.requests.menu.{CreateMenuRequest, DeleteMenuRequest}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object DeleteMenuScenario {

  val deleteMenu: ScenarioBuilder = scenario("CREATE then DELETE menu SUCCESS scenario")
    .feed(randomName)
    .exec(CreateMenuRequest.createMenu)
    .exec(DeleteMenuRequest.deleteMenu)

  val deleteMenuResourceNotFound: ScenarioBuilder = scenario("DELETE menu resource NOT FOUND scenario")
    .exec(DeleteMenuRequest.deleteMenuResourceNotFound)
}
