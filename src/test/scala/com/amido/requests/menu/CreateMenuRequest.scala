package com.amido.requests.menu

import com.amido.config.Configuration.baseUrl
import com.amido.utils.CreateMenuUtil
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._

object CreateMenuRequest {

  val requestBody = "{   \"name\": \"placeholderName\"," +
                    "\n  \"description\": \"placeholderDesc\"," +
                    "\n  \"enabled\": true," +
                    "\n  \"tenantId\": \"cbdc2f4c-f73e-11ea-adc1-0242ac120002\"}"

  var randomName = Iterator.continually(
                      Map(
                        "randName" -> requestBody.replaceAll("placeholder.*",
                          "PERF TEST " + CreateMenuUtil.randomString(20) +"\","))
  )

  val createMenu: HttpRequestBuilder = http("Create Menu")
    .post(baseUrl + "/v1/menu")
    .body(StringBody("""${randName}"""))
    .header("content-type", "application/json")
    .check(status is 201)
    .check(jsonPath("$.id").saveAs("menuId"))

  val createMenuError: HttpRequestBuilder = http("Create Menu Error")
    .post(baseUrl + "/v1/menu")
    .body(RawFileBody("./src/test/resources/bodies/CreateMenuError.json")).asJson
    .header("content-type", "application/json")
    .check(status is 400)
}
