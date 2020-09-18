package com.amido.config

import com.typesafe.config._

//This is where you set configuration values for the project
object Configuration {
  val environment: String =  System.getProperty("env")
  private val config: Config = ConfigFactory.load(s"$environment.application.properties")

  lazy val baseUrl: String = config.getString("baseUrl")

  val rampUpUsers: Int = Integer.getInteger("rampUsers", 1).toInt
  val rampUpUsersDuration: Int = Integer.getInteger("rampDuration", 1).toInt
  val onceUsers: Int = Integer.getInteger("atOnceUsers", 0).toInt
  val constUsersPerSec: Int = Integer.getInteger("constantUsersPerSec", 0).toInt
  val constUsersPerSecDuration: Int = Integer.getInteger("constUsersDuration",0).toInt
}