package com.amido.simulations.item

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.ItemScenarios
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, global, openInjectionProfileFactory, rampUsers}

import scala.concurrent.duration.DurationInt

class DeleteItemSimulation extends Simulation{
  private val deleteItemExec = ItemScenarios.deleteItemScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(deleteItemExec)
    .assertions(global.responseTime.max.lt(20000))
}
