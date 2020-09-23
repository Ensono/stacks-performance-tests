package com.amido.scenarios

import com.amido.requests.CategoryRequests.createCategoryFeed
import com.amido.requests.ItemRequests.{createItemFeed, updateItemFeed}
import com.amido.requests.MenuRequests.createMenuFeed
import com.amido.requests.{CategoryRequests, ItemRequests, MenuRequests}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object ItemScenarios {

  val createItemScenario = scenario("CREATE item scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .feed(createCategoryFeed)
    .exec(CategoryRequests.createCategory)
    .feed(createItemFeed)
    .exec(ItemRequests.createItem)

  val deleteItemScenario: ScenarioBuilder = scenario("CREATE then DELETE item SUCCESS scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .feed(createCategoryFeed)
    .exec(CategoryRequests.createCategory)
    .feed(createItemFeed)
    .exec(ItemRequests.createItem)
    .exec(ItemRequests.deleteItem)

  val updateItemScenario: ScenarioBuilder = scenario("CREATE then UPDATE item scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .feed(createCategoryFeed)
    .exec(CategoryRequests.createCategory)
    .feed(createItemFeed)
    .exec(ItemRequests.createItem)
    .feed(updateItemFeed)
    .exec(ItemRequests.updateItem)
}

