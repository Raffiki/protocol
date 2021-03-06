package spinoco.protocol.mail.header

import scodec.Codec
import spinoco.protocol.mail.header.codec.RFC2047Codec

/**
  * RFC 5322 3.6.5
  *
  * The informational fields are all optional.  The "Subject:" and
  * "Comments:" fields are unstructured fields as defined in section
  * 2.2.1, and therefore may contain text or folding white space.
  *
  */
case class Comments(comments: String) extends DefaultEmailHeaderField


object Comments extends DefaultHeaderDescription[Comments] {
  val codec: Codec[Comments] =
    RFC2047Codec.codec.xmap(Comments.apply, _.comments)
}