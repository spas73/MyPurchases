package cuzfrog.maila.server

import java.util.Properties

import javax.mail.{Session, Store, Transport}
import cuzfrog.maila.Configuration

private[maila] trait Server {
  def reader: Reader

  def sender: Sender
}

private[maila] object Server {
  def apply(config: Configuration): Server = {
    new JmServer(config)
  }

  private class JmServer(config: Configuration) extends Server {
    val properties: Properties = config.serverProps
    lazy val session: Session = Session.getInstance(properties)
    session.setDebug(config.javaMailDebug)
    lazy val store: Store = session.getStore(config.storeType)
    lazy val transport: Transport = session.getTransport(config.transportType)

    def reader: Reader = {
      store.connect(config.user, config.password)
      Reader(store, config)
    }

    def sender: Sender = {
      transport.connect(config.user, config.password)
      Sender(session, transport, config.user)
    }
  }

}