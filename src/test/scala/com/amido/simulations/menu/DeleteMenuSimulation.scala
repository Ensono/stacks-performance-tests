package com.amido.simulations.menu

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.MenuScenarios
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class DeleteMenuSimulation extends Simulation {

  private val deleteMenuExec = MenuScenarios.deleteMenuScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(deleteMenuExec)
    .assertions(global.responseTime.max.lt(20000))
}

