package org.chris_martin.sortimg

import scalax.io.StandardOpenOption._

object Sortimg {

  case class Point(x:Double, y:Double) {
    def binary_+(that:Point) = Point(x+that.x, y+that.y)
    def binary_-(that:Point) = Point(x-that.x, y-that.y)
    def binary_*(scalar:Double) = Point(x*scalar, y*scalar)
    def binary_/(scalar:Double) = Point(x/scalar, y/scalar)
  }

  trait BezierCommand {
    def pathStepString:String
  }

  case class M(p:Point) extends BezierCommand {
    def pathStepString = "M%f,%f".format(p.x, p.y)
  }

  case class C(p:Point, from:Point, to:Point) extends BezierCommand {
    def pathStepString = "M%f,%f".format(p.x, p.y)
  }

  case class Path() {

  }

  val arr = Seq(4, 5, 9)

  val a = Seq(
    <style type="text/css"><![CDATA[
      .Lane { fill: none; stroke-width: 5; stroke-linecap: round; stroke-linejoin: round; }
    ]]></style>
    <rect x="0" y="0" width="700" height="700" style="fill:white;" />
    <path d="M300,300 400,500 C400,400 420,450 460,460 S 400,400 500,500" stroke="red" class="Lane"/>
  )

  def main(args: Array[String]) {

    scalax.file.Path("/tmp/sortimg.svg").open(Seq(CreateFull, Write, Truncate)) { out =>

      out.append("""<?xml version="1.0" encoding="iso-8859-1"?>
        <!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 20001102//EN"
        "http://www.w3.org/TR/2000/CR-SVG-20001102/DTD/svg-20001102.dtd">""")

      out.append(
        <svg width="700" height="700">
          { a }
        </svg>.toString()
      )
    }
  }

}
