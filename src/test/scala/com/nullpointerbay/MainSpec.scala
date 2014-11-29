package com.nullpointerbay

import com.nullpointerbay.models.PostalCode

import scala.xml.{Utility, XML, Elem}

/**
 * Created by charafau on 11/29/14.
 */
class MainSpec extends UnitSpec {

  it should "create PostalCode from string" in {
    val line = "eh,Western Sahara,NNNNN,\"Wallis and Futuna belong to Morocco, it has the same postal codes.\",5"
    val parsedPostalCode: PostalCode = Main.parseLineToPostalCode(line)
    val expectedPostalCode = PostalCode("eh", "NNNNN")

    parsedPostalCode shouldBe expectedPostalCode

  }

  it should "create empty string when no value in csv" in {
    val line = "eh,Western Sahara,,\"Wallis and Futuna belong to Morocco, it has the same postal codes.\",5"
    val parsedPostalCode: PostalCode = Main.parseLineToPostalCode(line)
    val expectedPostalCode: PostalCode = PostalCode("eh", "")

    parsedPostalCode shouldBe expectedPostalCode

  }

  it should "serialize postal code to xml" in {
    val expectedXml = <postal_code>
      <id>eh</id>
      <mask>NNNNN</mask>
    </postal_code>

    val p = PostalCode("eh", "NNNNN")

    p.toXml shouldBe expectedXml

  }

  it should "create array of postal code in xml" in {
    val expectedXml = <postal_codes>
      <postal_code>
        <id>eh</id>
        <mask>NNNNN</mask>
      </postal_code> <postal_code>
        <id>jp</id>
        <mask>NNN-NNNN</mask>
      </postal_code>
    </postal_codes>

    val p1 = PostalCode("eh", "NNNNN")
    val p2 = PostalCode("jp", "NNN-NNNN")
    val postalCodes = List(p1, p2)

    val outputXml = Main.deserializeToXml(postalCodes)

    Utility.trim(outputXml) shouldBe Utility.trim(expectedXml)
  }


}
