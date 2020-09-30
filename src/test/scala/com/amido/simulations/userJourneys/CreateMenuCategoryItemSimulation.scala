package com.amido.simulations.userJourneys

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.UserScenarios
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, global, openInjectionProfileFactory, rampUsers}

import scala.concurrent.duration.DurationInt

class CreateMenuCategoryItemSimulation extends Simulation{
  private val createMenuCategoryItemExec = UserScenarios.createMenuCategoryItemScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(createMenuCategoryItemExec)
    .assertions(global.responseTime.max.lt(45000))
}
