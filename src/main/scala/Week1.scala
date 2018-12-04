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
  val lines = Source.fromResource("d2.txt").getLines
  var twice = 0
  var thrice = 0
  lines.foreach(line => {
    var ut = new FrequencyTable[Char]()
    val table = ut.freq(line.toList)
    var two = false
    var three = false
    for ((k, v) <- table) {
      if (v == 2) two = true
      if (v == 3) three = true
    }
    if (two) twice += 1
    if (three) thrice += 1
  })
  println(twice * thrice)
}

object Run extends App {
  // Day1
  Day2
}
