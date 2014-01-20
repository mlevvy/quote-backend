import com.google.inject.Guice

import play.api.GlobalSettings

object Global extends GlobalSettings {
  private val injector = Guice.createInjector()

  override def getControllerInstance[A](controllerClass: Class[A]) =
    injector.getInstance(controllerClass)
}