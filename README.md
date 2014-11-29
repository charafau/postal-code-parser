#postal-code-parser
==================

#Description

Very simple project for parsing csv data and storing some part of it to xml format.
Csv data must be formatted as: Country ID, some value, postal code format

Postal code format may contain:

* spaces ` `
* numeric fields as `N`
* letters as `A`
* separators `-`

#Compilation

If you want to compile project first you need to install `sbt`
and then simply invoke:

`sbt clean assembly`

compiled file will be created in `~/target/scala/` directory