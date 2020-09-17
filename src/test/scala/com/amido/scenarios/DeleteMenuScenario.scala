package com.amido.scenarios

import com.amido.requests.menu.CreateMenuRequest.randomName
import com.amido.requests.menu.{CreateMenuRequest, DeleteMenuRequest}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object DeleteMenuScenario {

  val deleteMenu : ScenarioBuilder = scenario("DELETE menu scenario")
    .feed(randomName)
    .exec(CreateMenuRequest.createMenu)
    .exec(DeleteMenuRequest.deleteMenu)

  val deleteMenuResourceNotFound : ScenarioBuilder = scenario("DELETE menu resource not found scenario")
    .exec(DeleteMenuRequest.deleteMenuResourceNotFound)
}
