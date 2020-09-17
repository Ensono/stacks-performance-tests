package com.amido.simulations

import com.amido.config.Configuration.{onceUsers, rampUpDuration, rampUpUsers}
import com.amido.scenarios.{CreateMenuScenario, DeleteMenuScenario}
import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class DeleteMenuSimulation extends Simulation {

  private val deleteMenuExec = DeleteMenuScenario.deleteMenu
    .inject(
      //atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpDuration seconds)
    )

  private val deleteMenuResourceNotFoundExec = DeleteMenuScenario.deleteMenuResourceNotFound
    .inject(
      //atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpDuration seconds)
    )

  setUp(deleteMenuResourceNotFoundExec)
    .assertions(global.responseTime.max.lt(5000))
}
