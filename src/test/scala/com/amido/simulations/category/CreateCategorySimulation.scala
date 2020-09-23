package com.amido.simulations.category

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.CategoryScenarios
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, global, openInjectionProfileFactory, rampUsers}

import scala.concurrent.duration._

class CreateCategorySimulation extends Simulation {
  private val createCategoryExec = CategoryScenarios.createCategoryScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(createCategoryExec)
    .assertions(global.responseTime.max.lt(45000))
}
