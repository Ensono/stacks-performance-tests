package com.amido.simulations.userJourneys

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.UserScenarios
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, global, openInjectionProfileFactory, rampUsers}

import scala.concurrent.duration.DurationInt

class UpdateSpecificItemSimulation extends Simulation{
  private val updateItemRampExec = UserScenarios.updateSpecificItemScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(updateItemRampExec)
    .assertions(global.responseTime.max.lt(45000))
}
