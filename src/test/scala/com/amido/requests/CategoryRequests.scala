package com.amido.requests

import com.amido.config.Configuration.baseUrl
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder._
import io.jvm.uuid.UUID

object CategoryRequests {

  private val headers = Map(
    "Accept" -> "application/json, text/javascript, */*; q=0.01",
    "Content-Type" -> "application/json")

  private val MenuPath = "/v1/menu/"
  private val CategoryPath = "/category/"

  val createCategoryFeed =
    Iterator.continually(Map("name" -> ("PERF TEST name " +  UUID.random),
                              "description" -> ("PERF TEST description " + UUID.random)))

  val updateCategoryFeed =
    Iterator.continually(Map("name" -> ("PERF TEST name updated" + UUID.random),
      "description" -> ("PERF TEST description updated" + UUID.random)))


  val createCategory: HttpRequestBuilder = http("Create Category")
    .post(baseUrl + MenuPath + "${menuId}" + CategoryPath)
    .body(ElFileBody("bodies/create-category.json"))
    .headers(headers)
    .check(status is 201)
    .check(jsonPath("$.id").saveAs("categoryId"))

  val deleteCategory: HttpRequestBuilder = http("Delete Category")
    .delete(baseUrl + MenuPath + "${menuId}" + CategoryPath + "${categoryId}")
    .check(status not 400, status not 401, status not 403, status not 404, status not 409)

  val updateCategory : HttpRequestBuilder = http("Update Category")
    .put(baseUrl + MenuPath + "${menuId}" + CategoryPath + "${categoryId}")
    .headers(headers)
    .body(ElFileBody("bodies/update-category.json"))
    .check(status is 200)
}
