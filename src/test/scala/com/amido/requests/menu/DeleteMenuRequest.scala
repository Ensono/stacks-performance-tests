package com.amido.requests.menu

import io.jvm.uuid._
import com.amido.config.Configuration.{baseUrl, rampUpDuration}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._


object DeleteMenuRequest {

  val deleteMenu: HttpRequestBuilder = http("Delete menu")
    .delete(baseUrl + "/v1/menu/${menuId}")
    .check(status not 400, status not 401, status not 403, status not 404, status not 409)

  val deleteMenuResourceNotFound : HttpRequestBuilder = http("Delete menu")
    .delete(baseUrl + "/v1/menu/" + UUID.random)
    .check(status is 404)

}
