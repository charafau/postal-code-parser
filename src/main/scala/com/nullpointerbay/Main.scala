package com.nullpointerbay

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.{Paths, Path, Files}

import com.nullpointerbay.models.PostalCode

import scala.io.Source
import scala.xml.{XML, Elem}

/**
 * Created by charafau on 11/29/14.
 */
object Main {
  def main(args: Array[String]) {

    if (args.length > 0) {


      val file = args(0)
      val lines = Source.fromFile(file).getLines
      var postalCodes = List[PostalCode]()

      for (x <- args) {
        println(x)
      }

      println("Parsing file...")
      for (line <- lines) {

        postalCodes = postalCodes ++ List(parseLineToPostalCode(line))

      }

      println("Creating xml...")
      val xml: Elem = deserializeToXml(postalCodes)

      println("Saving...")
      Files.write(Paths.get(new File(".").getAbsolutePath() + "/output.xml"), xml.toString().getBytes(StandardCharsets.UTF_8))

      println("Saved finished!")
    } else {
      println("Usage: ./app <csv_input_file_path>")
    }
  }

  def deserializeToXml(postalCodes: List[PostalCode]): Elem = {

    var sb = "<postal_codes>"
    for (postalCode <- postalCodes) {

      sb ++= postalCode.toXml.toString

    }

    sb ++= "</postal_codes>"

    val root = XML.loadString(sb)

    return root
  }

  def parseLineToPostalCode(line: String): PostalCode = {
    val postalCodeValues: Array[String] = line split ","
    val p = PostalCode(postalCodeValues(0), postalCodeValues(2))
    p
  }

}
