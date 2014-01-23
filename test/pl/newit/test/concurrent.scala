package pl.newit.test

import scala.concurrent.Await
import scala.concurrent.Awaitable
import scala.concurrent.duration._

package object concurrent {
  /** Waits 1 second and returns result. */
  def result[T](b: => Awaitable[T]) =
    Await.result(b, 1.second)
}