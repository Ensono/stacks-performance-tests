package com.amido.scenarios

import com.amido.requests.CategoryRequests.createCategoryFeed
import com.amido.requests.ItemRequests.{createItemFeed, updateItemFeed}
import com.amido.requests.MenuRequests.createMenuFeed
import com.amido.requests.{CategoryRequests, ItemRequests, MenuRequests}
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder

object UserScenarios {

  //Simulates a user fetching all available menus
  val getAllMenusScenario = scenario("GET all menus scenario")
    .exec(MenuRequests.getMenuCollection)

  //Simulates a user fetching one specific menu
  val getSpecificMenuScenario = scenario("GET specific menu scenario")
    .exec(MenuRequests.getMenuResource)

  //Simulates creating menu with a category and an item
  val createMenuCategoryItemScenario = scenario("CREATE menu, category and item scenario")
    .feed(createMenuFeed)
    .exec(MenuRequests.createMenu)
    .feed(createCategoryFeed)
    .exec(CategoryRequests.createCategory)
    .feed(createItemFeed)
    .exec(ItemRequests.createItem)

  //Simulates an item to be updated in a menu
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

