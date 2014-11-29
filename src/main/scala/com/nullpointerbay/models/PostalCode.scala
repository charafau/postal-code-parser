package com.nullpointerbay.models

/**
 * Created by charafau on 11/29/14.
 */
case class PostalCode(countryId: String, codeFormat: String) {

  def toXml = {
    <postal_code>
      <id>{countryId}</id>
      <mask>{codeFormat}</mask>
    </postal_code>
  }

}
