package spinoco.protocol.ldap

import scodec.Codec
import spinoco.protocol.ldap.elements.PartialAttribute.PartialAttributeList
import spinoco.protocol.ldap.elements.{LdapDN, PartialAttribute}

/**
  * A result of search. Contains one resulting entry.
  *
  * @param objectName The identification for the object that represents this entry.
  * @param attributes The attributes of this entry. These attributes do not have to have values,
  *                   they may only contain descriptions of their attributes.
  */
case class SearchResultEntry(
  objectName: LdapDN
  , attributes: PartialAttributeList
) extends ProtocolOp

object SearchResultEntry {

  // Codec without the BER wrapping
  val codecInner: Codec[SearchResultEntry] =
    (LdapDN.codec :: PartialAttribute.listCodec).as[SearchResultEntry]

}