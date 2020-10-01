package com.amido.simulations.item

import com.amido.config.Configuration.{onceUsers, rampUpUsers, rampUpUsersDuration}
import com.amido.scenarios.ItemScenarios
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, global, openInjectionProfileFactory, rampUsers}

import scala.concurrent.duration.DurationInt

class UpdateItemSimulation extends Simulation{
  private val updateItemExec = ItemScenarios.updateItemScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  // 20000 was set for local runs, should decrease to 2000 for runs in cloud
  setUp(updateItemExec)
    .assertions(global.responseTime.max.lt(20000))
}
