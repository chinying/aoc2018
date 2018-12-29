package aoc18

import scala.collection.mutable.HashSet
import geometry.Coordinates

package object geometry {
  type Coordinates = (Int, Int)
}

case class Rectangle (tl: Coordinates, br: Coordinates) {
  var pts = new HashSet[Coordinates]
  lazy val points = {
    for (i <- tl._1 to br._1; j <- tl._2 to br._2) pts += ((i, j))
    pts
  }
}
