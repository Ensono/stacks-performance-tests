package com.amido.requests.menu

import com.amido.config.Configuration.baseUrl
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._

object GetMenuRequest {
  val getMenuCollection: HttpRequestBuilder = http("Get menu collection")
    .get(baseUrl + "/v1/menu")
    .header("Accept", "application/json")
    .check(status is 200)

  val getMenu: HttpRequestBuilder = http("Get menu resource")
    .get(baseUrl + "/v1/menu/${menuId}")
    .header("Accept", "application/json")
    .check(status is 200)
    .check(jsonPath("$.description").exists)
}
