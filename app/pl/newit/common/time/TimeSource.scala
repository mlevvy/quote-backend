package pl.newit.common.time

import org.joda.time.DateTime
import org.joda.time.DateTimeZone

trait TimeSource {
  /** Obtains a DateTime set to the current system millisecond time in the default time zone. */
  def now(): DateTime

  def now(zone: DateTimeZone): DateTime
}