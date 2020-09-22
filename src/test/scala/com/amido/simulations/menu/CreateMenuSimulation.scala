package com.amido.simulations.menu

import com.amido.config.Configuration._
import com.amido.scenarios.MenuScenarios
import io.gatling.core.Predef._

import scala.concurrent.duration._

class CreateMenuSimulation extends Simulation {

  private val createMenuExec = MenuScenarios.createMenuScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(createMenuExec)
    .assertions(global.responseTime.max.lt(45000))
}
