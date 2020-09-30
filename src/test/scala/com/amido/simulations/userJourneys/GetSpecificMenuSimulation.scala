package com.amido.simulations.userJourneys

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.{MenuScenarios, UserScenarios}
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, global, openInjectionProfileFactory, rampUsers}

import scala.concurrent.duration.DurationInt

class GetSpecificMenuSimulation extends Simulation{
  private val createMenuExec = MenuScenarios.createMenuScenario
  private val getSpecificMenuRampExec = UserScenarios.getSpecificMenuScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(getSpecificMenuRampExec)
    .assertions(global.responseTime.max.lt(45000))
}
