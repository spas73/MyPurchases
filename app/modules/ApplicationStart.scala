package modules


import com.google.inject.AbstractModule
import services.StartUpService

// A Module is needed to register bindings
class ApplicationStart extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[StartUpService]).asEagerSingleton()
  }
}