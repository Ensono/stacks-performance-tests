package com.amido.requests.menu

import com.amido.config.Configuration.baseUrl
import com.amido.config.EndPoints.MenuPath
import com.amido.utils.MenuUtil
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._

object CreateMenuRequest {

  val createMenuErrorResource = getClass.getResource("/bodies/CreateMenuError.json")

  val requestBody = "{   \"name\": \"placeholderName\"," +
                    "\n  \"description\": \"placeholderDesc\"," +
                    "\n  \"enabled\": true," +
                    "\n  \"tenantId\": \"cbdc2f4c-f73e-11ea-adc1-0242ac120002\"}"

  var randomName = Iterator.continually(
                      Map(
                        "randName" -> requestBody.replaceAll("placeholder.*",
                          "PERF TEST " + MenuUtil.randomString(20) +"\",")))

  var httpHeaders = Map("Content-Type" -> "application/json")


  val createMenu: HttpRequestBuilder = http("Create Menu")
    .post(baseUrl + MenuPath)
    .body(StringBody("""${randName}"""))
    .headers(httpHeaders)
    .check(status is 201)
    .check(jsonPath("$.id").saveAs("menuId"))

  val createMenuError: HttpRequestBuilder = http("Create Menu Error")
    .post(baseUrl + MenuPath)
    .body(RawFileBody(createMenuErrorResource.getPath())).asJson
    .headers(httpHeaders)
    .check(status is 400)
}
