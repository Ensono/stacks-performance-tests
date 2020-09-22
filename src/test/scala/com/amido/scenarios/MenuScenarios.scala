package com.amido.scenarios

import com.amido.requests.MenuRequests
import com.amido.requests.MenuRequests.{createMenuFeed, updateMenuFeed}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object MenuScenarios {

  val createMenuScenario = scenario("CREATE menu scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)

  val getMenuCollectionScenario = scenario("GET menu collection scenario")
    .exec(MenuRequests.getMenuCollection)

  val getMenuResourceScenario = scenario("GET menu resource scenario")
    .exec(MenuRequests.getMenuResource)

  val deleteMenuScenario: ScenarioBuilder = scenario("CREATE then DELETE menu SUCCESS scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .exec(MenuRequests.deleteMenu)

  val updateMenuScenario: ScenarioBuilder = scenario("CREATE then UPDATE menu scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .feed(updateMenuFeed)
    .exec(MenuRequests.updateMenu)
}

