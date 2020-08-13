package com.amido.requests


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import com.amido.config.Configuration.baseUrl

object CreateMenuRequest {
  var create_menu: HttpRequestBuilder = http("Create Menu")
    .post(baseUrl + "/v1/menu")
    .body(RawFileBody("./src/test/resources/bodies/CreateMenu.json")).asJson
    .header("content-type", "application/json")
    .check(status is 201)
}
