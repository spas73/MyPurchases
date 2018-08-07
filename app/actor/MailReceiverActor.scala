package actor

import java.util.Properties

import actor.MailReceiverActor.Check
import akka.actor.{Actor, Props}
import cuzfrog.maila.Maila
import javax.mail.internet.MimeMessage
import javax.mail._

class MailReceiverActor(host: String, port: Int, user: String, password: String, inboxName: String) extends Actor {


  def processMessage(message : MimeMessage) : Array[Unit] = {
    println("ok")
    null

  }


  override def receive: Receive = {
    case Check =>
      val props = System.getProperties
      props.setProperty("mail.store.protocol", "imaps")
      val session = Session.getDefaultInstance(props, null)
      val store = session.getStore("imaps")
      try {
        store.connect(host, port, user, password)
        val inbox = store.getFolder(inboxName)
        inbox.open(Folder.READ_ONLY)
        inbox.getMessages map {
          case message:MimeMessage => processMessage(message) // define this function to handle the message
          case _ => // do nothing or log that you only process MimeMessages
        }
        inbox.close(true)
      } catch {
        case e @ (_:NoSuchProviderException|_:MessagingException) => // log the error
      } finally {
        store.close()
      }
  }
}

object MailReceiverActor {
  case object Check{

  }

  def props(host:String, port:Int, user:String, password:String, inboxName:String) = {
    Props(new MailReceiverActor(host, port, user, password, inboxName))
  }

}

object CheckingMails {

  def check(host: String, storeType: String, user: String, password: String): Unit = {
    try { //create properties field
      val properties = new Properties
      properties.put("mail.pop3.host", host)
      properties.put("mail.pop3.port", "995")
      properties.put("mail.pop3.starttls.enable", "true")
      val emailSession = Session.getDefaultInstance(properties)
      //create the POP3 store object and connect with the pop server
      val store = emailSession.getStore("pop3s")
      store.connect(host, user, password)
      //create the folder object and open it
      val emailFolder = store.getFolder("INBOX")
      emailFolder.open(Folder.READ_ONLY)
      // retrieve the messages from the folder in an array and print it
      val messages = emailFolder.getMessages
      System.out.println("messages.length---" + messages.length)
      var i = 0
      val n = messages.length
      while ( {
        i < n
      }) {
        val message: Message = messages(i)
        System.out.println("---------------------------------")
        System.out.println("Email Number " + (i + 1))
        System.out.println("Subject: " + message.getSubject)
        System.out.println("From: " + message.getFrom.toString)
        System.out.println("Text: " + message.getContent.toString)


        {
          i += 1; i - 1
        }
      }
      //close the store and folder objects
      emailFolder.close(false)
      store.close()
    } catch {
      case e: NoSuchProviderException =>
        e.printStackTrace()
      case e: MessagingException =>
        e.printStackTrace()
      case e: Exception =>
        e.printStackTrace()
    }
  }

  def main(args: Array[String]): Unit = {
    val host = "pop.gmail.com"
    // change accordingly
    val mailStoreType = "pop3"
    val username = "mailservice1973@gmail.com"
    val password = "bykcvomtvsnkcbwd"
    check(host, mailStoreType, username, password)
  }
}



object xxx {
  def main(args: Array[String]): Unit = {

    val maila = Maila.newInstance(askUser = "mailservice1973@gmail.com" ,askPassword = "tkniommdyolzowgm")
//    val mails1 = maila.read() //get a List of mails using default filter.

    //      val filter = MailFilter(
    //        maxSearchAmount = 30,
    //        filter = _.subject.contains("myKeyWord")
    //      )
    val mails2 = maila.read() //get a List of mails
    mails2.foreach(m =>
      {
        println(m.contentText)
        println("--------------------------------------------------------------")
      }



    ) //print text content

  }

}

