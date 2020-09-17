package com.amido.simulations

import com.amido.config.Configuration.{onceUsers, rampUpDuration, rampUpUsers}
import com.amido.scenarios.{CreateMenuScenario, UpdateMenuScenario}
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class UpdateMenuSimulation extends Simulation {

  private val updateMenuExec = UpdateMenuScenario.updateMenu
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpDuration seconds))

  // 20000 was set for local runs, should decrease to 2000 for runs in cloud
  setUp(updateMenuExec)
    .assertions(global.responseTime.max.lt(20000))
}
