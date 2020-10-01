package com.amido.requests

import com.amido.config.Configuration.baseUrl
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import io.jvm.uuid.UUID

object ItemRequests {

  private val headers = Map(
    "Accept" -> "application/json, text/javascript, */*; q=0.01",
    "Content-Type" -> "application/json")

  private val MenuPath = "/v1/menu/"
  private val CategoryPath = "/category/"
  private val ItemPath = "/items/"

  val createItemFeed =
    Iterator.continually(Map("name" -> ("PERF TEST name " +  UUID.random),
                              "description" -> ("PERF TEST description " + UUID.random),
                              "price" -> ("99.9")))

  val updateItemFeed =
    Iterator.continually(Map("name" -> ("PERF TEST name updated" + UUID.random),
                             "description" -> ("PERF TEST description updated" + UUID.random)))



  val createItem: HttpRequestBuilder = http("Create Item")
    .post(baseUrl + MenuPath + "${menuId}" + CategoryPath + "${categoryId}" + ItemPath)
    .body(ElFileBody("bodies/create-item.json"))
    .headers(headers)
    .check(status is 201)
    .check(jsonPath("$.id").saveAs("itemId"))

  val deleteItem: HttpRequestBuilder = http("Delete Item")
    .delete(baseUrl + MenuPath + "${menuId}" + CategoryPath + "${categoryId}" + ItemPath + "${itemId}")
    .check(status not 400, status not 401, status not 403, status not 404, status not 409)

  val updateItem : HttpRequestBuilder = http("Update Item")
    .put(baseUrl + MenuPath + "${menuId}" + CategoryPath + "${categoryId}" + ItemPath + "${itemId}")
    .headers(headers)
    .body(ElFileBody("bodies/update-item.json"))
    .check(status is 200)
}
