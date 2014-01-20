package pl.newit.common.time

import org.joda.time.DateTime

trait TimeSource {
  def now(): DateTime
}