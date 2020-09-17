package com.amido.requests.menu

import com.amido.config.Configuration.baseUrl
import com.amido.utils.CreateMenuUtil
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._


object UpdateMenuRequest {

  val requestBody = "{   \"name\": \"placeholderName\"," +
    "\n  \"description\": \"placeholderDesc\"," +
    "\n  \"enabled\": false }"

  var randomNameUpdate = Iterator.continually(
    Map(
      "randName" -> requestBody.replaceAll("placeholder.*",
        "PERF TEST updated " + CreateMenuUtil.randomString(20) +"\","))
  )

  val updateMenu : HttpRequestBuilder = http("Update menu")
    .put(baseUrl + "/v1/menu/${menuId}")
    .header("content-type", "application/json")
    .body(StringBody("""${randName}"""))
    .check(status is 200)


}
