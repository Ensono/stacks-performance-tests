package com.amido.simulations.menu

import com.amido.config.Configuration.{onceUsers, rampUpUsersDuration, rampUpUsers}
import com.amido.scenarios.menu.DeleteMenuScenario
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class DeleteMenuSimulation extends Simulation {

  private val deleteMenuExec = DeleteMenuScenario.deleteMenu
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  private val deleteMenuResourceNotFoundExec = DeleteMenuScenario.deleteMenuResourceNotFound
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(deleteMenuExec)
    .assertions(global.responseTime.max.lt(20000))
}
