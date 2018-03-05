package spinoco.protocol.mail.header

import scodec.Codec
import scodec.codecs._
import shapeless.tag.@@
import spinoco.protocol.mail.header.codec._

/**
  * RFC 5322 3.6.4
  *
  *  The "In-Reply-To:" and "References:" fields are used when creating a
  *  reply to a message.  They hold the message identifier of the original
  *  message and the message identifiers of other messages (for example,
  *  in the case of a reply to a message that was itself a reply).  The
  *  "In-Reply-To:" field may be used to identify the message (or
  *  messages) to which the new message is a reply, while the
  *  "References:" field may be used to identify a "thread" of
  *  conversation.
  *
  * @param msgId    Id of the message
  * @param others   Id of other messages
  */
case class References (
msgId: String @@ `Message-ID`
, others: List[String @@ `Message-ID`]
) extends DefaultEmailHeaderField


object References extends DefaultHeaderDescription[References] {
  val codec: Codec[References] =
    cfwsSeparated(choice(msgIdCodec, toMsgIdCodec(atomString), toMsgIdCodec(quotedString)))
    .xmap(References.apply _ tupled, { rfr => (rfr.msgId, rfr.others) })

}