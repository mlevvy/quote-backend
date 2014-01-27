import com.google.inject.Guice

import controllers.ControllerModule
import play.api.GlobalSettings

object Global extends GlobalSettings {
  private val injector = Guice.createInjector(ControllerModule)

  override def getControllerInstance[A](controllerClass: Class[A]) =
    injector.getInstance(controllerClass)
}