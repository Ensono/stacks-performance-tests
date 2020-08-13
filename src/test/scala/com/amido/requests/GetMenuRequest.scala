package com.amido.requests

import com.amido.config.Configuration.baseUrl
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

//Requests represent the REST requests
object GetMenuRequest {
  var get_menu: HttpRequestBuilder = http("Get Menu")
    .get(baseUrl + "/v1/menu")
    .check(status is 200)
}
