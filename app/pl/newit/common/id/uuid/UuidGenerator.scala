package pl.newit.common.id.uuid
import java.nio.ByteBuffer
import java.util.UUID

import com.google.common.io.BaseEncoding
import com.google.common.primitives.Longs

import pl.newit.common.id.UniqueIdGenerator

private[uuid] class UuidGenerator extends UniqueIdGenerator {
  val encoder = BaseEncoding.base64Url.omitPadding

  def toString(uuid: UUID) =
    encoder.encode(
      ByteBuffer.allocate(Longs.BYTES * 2)
        .putLong(uuid.getMostSignificantBits)
        .putLong(uuid.getLeastSignificantBits)
        .array)

  override def generate() =
    toString(UUID.randomUUID())
}