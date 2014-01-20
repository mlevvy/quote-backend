package pl.newit.common.time

import org.joda.time.DateTime

/** Default time source. */
private[time] class JodaTimeSource extends TimeSource {
  /** Obtains a DateTime set to the current system millisecond time in the default time zone. */
  override def now() =
    DateTime.now()
}
