package com.amido.simulations.menu

import com.amido.config.Configuration._
import com.amido.scenarios.menu.CreateMenuScenario
import io.gatling.core.Predef._

import scala.concurrent.duration._

class CreateMenuSimulation extends Simulation {

  private val createMenuExec = CreateMenuScenario.createMenu
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  private val createMenuErrorRampExec = CreateMenuScenario.createMenuError
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(createMenuExec)
    .assertions(global.responseTime.max.lt(45000))
}
