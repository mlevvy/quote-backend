package pl.newit.common.time

import org.joda.time.DateTime
import org.joda.time.DateTimeZone

/** Default time source. */
private[time] class JodaTimeSource extends TimeSource {
  override def now() =
    DateTime.now()

  override def now(zone: DateTimeZone) =
    DateTime.now(zone)
}
