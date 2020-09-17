package com.amido.simulations

import com.amido.config.Configuration._
import com.amido.scenarios.CreateMenuScenario
import io.gatling.core.Predef._

import scala.concurrent.duration._

class CreateMenuSimulation extends Simulation {
  private val createMenuRampExec = CreateMenuScenario.createMenu
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpDuration seconds)
    )

  private val createMenuErrorRampExec = CreateMenuScenario.createMenuError
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpDuration seconds)
    )

  setUp(createMenuErrorRampExec)
    .assertions(global.responseTime.max.lt(45000))
}
