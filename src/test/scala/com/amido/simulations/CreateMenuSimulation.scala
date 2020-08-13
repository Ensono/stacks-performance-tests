package com.amido.simulations

import com.amido.config.Configuration._
import com.amido.scenarios.CreateMenuScenario
import io.gatling.core.Predef._

import scala.concurrent.duration._

class CreateMenuSimulation extends Simulation {
  private val createMenuRampExec = CreateMenuScenario.createMenuScenario
    .inject(rampUsers(users) during(rampup seconds))

  setUp(createMenuRampExec)
    .assertions(global.responseTime.max.lt(5000))
}
