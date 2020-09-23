package com.amido.scenarios

import com.amido.requests.CategoryRequests.{createCategoryFeed, updateCategoryFeed}
import com.amido.requests.MenuRequests.createMenuFeed
import com.amido.requests.{CategoryRequests, MenuRequests}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object CategoryScenarios {

  val createCategoryScenario = scenario("CREATE category scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .feed(createCategoryFeed)
    .exec(MenuRequests.createMenu)

  val deleteCategoryScenario: ScenarioBuilder = scenario("CREATE then DELETE category SUCCESS scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .feed(createCategoryFeed)
    .exec(CategoryRequests.createCategory)
    .exec(CategoryRequests.deleteCategory)

  val updateCategoryScenario: ScenarioBuilder = scenario("CREATE then UPDATE category scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .feed(createCategoryFeed)
    .exec(CategoryRequests.createCategory)
    .feed(updateCategoryFeed)
    .exec(CategoryRequests.updateCategory)
}

