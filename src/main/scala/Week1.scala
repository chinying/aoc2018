package aoc18

import io.Source
import scala.collection.mutable.HashSet

object Day1 {
  val lines = Source.fromResource("d1-1.txt").getLines.toList
  //lines.foreach(x => println(x.tail))
  // part 1
  val ans1 = lines.foldLeft(0) { (acc, i) => {
      acc + i.toInt
    }
  }
  println(ans1)

  var seenSet = new HashSet[Int]()

  lazy val circularList = Iterator.continually(lines).flatten
  var flag = true
  var cumSum = 0
  while (flag) {
    val nxt = circularList.next().toInt
    if (seenSet.contains(cumSum)) {
      flag = false
      println(cumSum)
    }
    seenSet += cumSum
    cumSum += nxt
  }
}

object Day2 {
  
}

object Run extends App {
  Day1
}
