package com.amido.requests.menu

import io.jvm.uuid._
import com.amido.config.Configuration.baseUrl
import com.amido.config.EndPoints.MenuPath
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._

object GetMenuRequest {

  var httpHeaders = Map("Accept" -> "application/json")

  val getMenuCollection: HttpRequestBuilder = http("Get menu collection")
    .get(baseUrl + MenuPath)
    .headers(httpHeaders)
    .check(status is 200)

  val getMenu: HttpRequestBuilder = http("Get menu resource")
    .get(baseUrl + MenuPath + "${menuId}")
    .headers(httpHeaders)
    .check(status is 200)
    .check(jsonPath("$.description").exists)

  val getMenuResourceNotFound : HttpRequestBuilder = http("Get menu resource not found")
    .get(baseUrl + MenuPath + UUID.random)
    .headers(httpHeaders)
    .check(status is 404)
}
