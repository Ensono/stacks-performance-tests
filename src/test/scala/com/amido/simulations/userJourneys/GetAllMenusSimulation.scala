package com.amido.simulations.userJourneys

import com.amido.config.Configuration._
import com.amido.scenarios.UserScenarios
import io.gatling.core.Predef._

import scala.concurrent.duration._

//Simulations are where you define the load that will be injected to the server
//Docs: https://gatling.io/docs/current/general/simulation_structure/
//Docs: https://gatling.io/docs/current/general/simulation_setup
class GetAllMenusSimulation extends Simulation {
  private val getAllMenusRampExec = UserScenarios.getAllMenusScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )

  setUp(getAllMenusRampExec)
    .assertions(global.responseTime.max.lt(45000))
}
