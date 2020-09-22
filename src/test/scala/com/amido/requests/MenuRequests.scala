package com.amido.requests

import com.amido.config.Configuration.baseUrl
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import io.jvm.uuid.UUID

object MenuRequests {

  private val headers = Map(
    "Accept" -> "application/json, text/javascript, */*; q=0.01",
    "Content-Type" -> "application/json")

  private val MenuPath = "/v1/menu/"

  val createMenuFeed =
    Iterator.continually(Map("name" -> ("PERF TEST name " +  UUID.random),
                              "description" -> ("PERF TEST description " + UUID.random)))

  val updateMenuFeed =
    Iterator.continually(Map("name" -> ("PERF TEST name updated" + UUID.random),
      "description" -> ("PERF TEST description updated" + UUID.random)))


  val createMenu: HttpRequestBuilder = http("Create Menu")
    .post(baseUrl + MenuPath)
    .body(ElFileBody("bodies/create-menu.json"))
    .headers(headers)
    .check(status is 201)
    .check(jsonPath("$.id").saveAs("menuId"))

  val getMenuCollection: HttpRequestBuilder = http("Get menu collection")
    .get(baseUrl + MenuPath)
    .headers(headers)
    .check(status is 200)

  val getMenuResource: HttpRequestBuilder = http("Get menu resource")
    .get(baseUrl + MenuPath + "${menuId}")
    .headers(headers)
    .check(status is 200)
    .check(jsonPath("$.description").exists)

  val deleteMenu: HttpRequestBuilder = http("Delete menu")
    .delete(baseUrl + MenuPath + "${menuId}")
    .check(status not 400, status not 401, status not 403, status not 404, status not 409)

  val updateMenu : HttpRequestBuilder = http("Update menu")
    .put(baseUrl + MenuPath + "${menuId}")
    .headers(headers)
    .body(ElFileBody("bodies/update-menu.json"))
    .check(status is 200)
}
