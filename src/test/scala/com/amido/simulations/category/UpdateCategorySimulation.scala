package com.amido.simulations.category

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.{CategoryScenarios}
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, global, openInjectionProfileFactory, rampUsers}

import scala.concurrent.duration.DurationInt

class UpdateCategorySimulation extends Simulation{
  private val updateCategoryExec = CategoryScenarios.updateCategoryScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  // 20000 was set for local runs, should decrease to 2000 for runs in cloud
  setUp(updateCategoryExec)
    .assertions(global.responseTime.max.lt(20000))
}
