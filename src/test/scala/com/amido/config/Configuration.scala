package com.amido.config

import com.typesafe.config._

//This is where you set configuration values for the project
object Configuration {
  val environment: String =  System.getProperty("env")
  private val config: Config = ConfigFactory.load(s"$environment.application.properties")
  // When intending to run in the pipeline modify below variable to following: config.getString("baseUrl")
  lazy val baseUrl: String = "http://localhost:9000"

  val rampUpUsers: Int = Integer.getInteger("rampUsers", 1).toInt
  val rampUpDuration: Int = Integer.getInteger("rampDuration", 1).toInt
  val onceUsers: Int = Integer.getInteger("atOnceUsers", 0).toInt
  val constUsersPerSec: Int = Integer.getInteger("constUsersPerSec", 0).toInt
  val constUsersPerSecDuration: Int = Integer.getInteger("constUsersDuration",0).toInt
}

