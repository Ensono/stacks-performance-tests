package com.amido.requests.menu

import com.amido.config.Configuration.baseUrl
import com.amido.config.EndPoints.MenuPath
import com.amido.utils.MenuUtil
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import io.jvm.uuid.UUID


object UpdateMenuRequest {

  val updateMenuErrorResource = getClass.getResource("/bodies/UpdateMenuError.json")
  val updateMenuResource = getClass.getResource("/bodies/UpdateMenu.json")

  val requestBody = "{   \"name\": \"placeholderName\"," +
    "\n  \"description\": \"placeholderDesc\"," +
    "\n  \"enabled\": false }"

  var randomNameUpdate = Iterator.continually(
    Map(
      "randName" -> requestBody.replaceAll("placeholder.*",
        "PERF TEST updated " + MenuUtil.randomString(20) +"\","))
  )

  var httpHeaders = Map("Content-Type" -> "application/json")

  val updateMenu : HttpRequestBuilder = http("Update menu")
    .put(baseUrl + MenuPath + "${menuId}")
    .headers(httpHeaders)
    .body(StringBody("""${randName}"""))
    .check(status is 200)

  val updateMenuError : HttpRequestBuilder = http("Update menu error")
    .put(baseUrl + MenuPath + "${menuId}")
    .headers(httpHeaders)
    .body(RawFileBody(updateMenuErrorResource.getPath())).asJson
    .check(status is 400)

  val updateMenuResourceNotFound : HttpRequestBuilder = http("Update menu resource not found")
    .put(baseUrl + MenuPath + UUID.random)
    .headers(httpHeaders)
    .body(RawFileBody(updateMenuResource.getPath())).asJson
    .check(status is 404)
}

