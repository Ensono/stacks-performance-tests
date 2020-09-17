package com.amido.utils

object CreateMenuUtil {
  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString
}
