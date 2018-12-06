package aoc18

import io.Source
import scala.collection.mutable.{HashSet, ListBuffer}

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
  val lines = Source.fromResource("d2.txt").getLines.toList
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


  def differ(x: String, y: String): Int = {
    var count = 0
    // count += math.abs(x.length - y.length).toInt
    // assumption that strings are of same length
    for (i <- 0 until x.length) {
      if (x(i) != y(i)) count += 1
    }
    count
  }

  lines.combinations(2).foreach(pair => {
    val distance = differ(pair.head, pair.last)
    if (distance == 1) {
      val x = pair.head
      val y = pair.last

      // println(x.toSet.intersect(y.toSet).mkString)
      var ans2 = new ListBuffer[Char]()
      for (i <- 0 until x.length) {
        if (x(i) == y(i)) ans2 += x(i)
      }
      println(ans2.mkString)
    }
  })

}
object Run extends App {
  // Day1
  Day2
}
