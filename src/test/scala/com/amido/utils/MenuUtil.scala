package com.amido.utils

object MenuUtil {
  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString
}
