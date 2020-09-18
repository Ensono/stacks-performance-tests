package com.amido.scenarios.menu

import com.amido.requests.menu.CreateMenuRequest.randomName
import com.amido.requests.menu.{CreateMenuRequest, GetMenuRequest}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object GetMenuScenario {
  val getMenuCollection: ScenarioBuilder = scenario("GET menu collection SUCCESS scenario")
    .exec(GetMenuRequest.getMenuCollection)

  val getMenuResourceNotFound: ScenarioBuilder = scenario("GET menu resource NOT FOUND scenario")
    .exec(GetMenuRequest.getMenuResourceNotFound)

  val getMenuResource: ScenarioBuilder = scenario("CREATE then GET menu resource SUCCESS scenario")
    .feed(randomName)
    .exec(CreateMenuRequest.createMenu)
    .exec(GetMenuRequest.getMenu)
}
