package com.amido.simulations.menu

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.MenuScenarios
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class UpdateMenuSimulation extends Simulation {

  private val updateMenuExec = MenuScenarios.updateMenuScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  // 20000 was set for local runs, should decrease to 2000 for runs in cloud
  setUp(updateMenuExec)
    .assertions(global.responseTime.max.lt(20000))
}
