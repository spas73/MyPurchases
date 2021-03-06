package cuzfrog.maila

import javax.mail.Message

case class MailFilter(
                       maxSearchAmount: Int = 30,
                       filter: (Mail => Boolean) = Mail => true,
                       javaMessageFilter: (Message => Boolean) = Message => true)

object MailFilter {
  lazy val default = new MailFilter()
}