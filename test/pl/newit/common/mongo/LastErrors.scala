package pl.newit.common.mongo
import reactivemongo.core.commands.LastError

object LastErrors {
  val nothingUpdated = LastError(
    ok = true,
    err = None,
    code = None,
    errMsg = None,
    originalDocument = None,
    updated = 0,
    updatedExisting = false)

  val oneUpdated = LastError(
    ok = true,
    err = None,
    code = None,
    errMsg = None,
    originalDocument = None,
    updated = 1,
    updatedExisting = false)

  val oneExistingUpdated = LastError(
    ok = true,
    err = None,
    code = None,
    errMsg = None,
    originalDocument = None,
    updated = 1,
    updatedExisting = true)
}