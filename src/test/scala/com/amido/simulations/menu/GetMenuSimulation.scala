package com.amido.simulations.menu

import com.amido.config.Configuration._
import com.amido.scenarios.MenuScenarios
import io.gatling.core.Predef._

import scala.concurrent.duration._

//Simulations are where you define the load that will be injected to the server
//Docs: https://gatling.io/docs/current/general/simulation_structure/
//Docs: https://gatling.io/docs/current/general/simulation_setup
class GetMenuSimulation extends Simulation {
  private val getMenuRampExec = MenuScenarios.getMenuCollectionScenario
    .inject(
      //rampConcurrentUsers(users) to (users + 10) during (rampup seconds),
      //constantConcurrentUsers(users) during (rampup seconds),
      rampUsers(rampUpUsers) during (rampUpUsersDuration seconds),
      //nothingFor(4 seconds),
      //atOnceUsers(10),
      //rampUsers(10) during (5 seconds),
      constantUsersPerSec(constUsersPerSecDuration) during (constUsersPerSecDuration seconds)
      //constantUsersPerSec(20) during (15 seconds) randomized,
      //rampUsersPerSec(10) to 20 during (10 minutes),
      //rampUsersPerSec(10) to 20 during (10 minutes) randomized,
      //heavisideUsers(1000) during (20 seconds)
    )

  private val getMenuResourceExec = MenuScenarios.getMenuResourceScenario
    .inject(
      atOnceUsers(onceUsers),
      rampUsers(rampUpUsers) during(rampUpUsersDuration seconds)
    )


  setUp(getMenuRampExec)
    // 20000 was set for local runs, should decrease to 2000 for runs in cloud
    .assertions(global.responseTime.max.lt(20000))
}
