package com.amido.simulations.category

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.CategoryScenarios
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, global, openInjectionProfileFactory, rampUsers}

import scala.concurrent.duration.DurationInt

class DeleteCategorySimulation extends Simulation{
  private val deleteCategoryExec = CategoryScenarios.deleteCategoryScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(deleteCategoryExec)
    .assertions(global.responseTime.max.lt(20000))
}
