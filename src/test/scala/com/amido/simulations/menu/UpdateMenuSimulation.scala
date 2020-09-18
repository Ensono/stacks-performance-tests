package com.amido.simulations.menu

import com.amido.config.Configuration.{onceUsers, rampUpUsersDuration, rampUpUsers}
import com.amido.scenarios.menu.UpdateMenuScenario
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class UpdateMenuSimulation extends Simulation {

  private val updateMenuExec = UpdateMenuScenario.updateMenu
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  private val updateMenErrorExec = UpdateMenuScenario.updateMenuError
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  private val updateMenuResourceNotFoundExec = UpdateMenuScenario.updateMenuResourceNotFound
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  // 20000 was set for local runs, should decrease to 2000 for runs in cloud
  setUp(updateMenuExec)
    .assertions(global.responseTime.max.lt(20000))
}
