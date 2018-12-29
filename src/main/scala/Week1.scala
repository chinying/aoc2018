package aoc18

import io.Source
import java.util.concurrent.ForkJoinPool
import scala.collection.mutable.{HashSet, ListBuffer}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.{Failure, Success}

import geometry._

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

trait Day3 {

  // left, down
  def parseCoordinates(xy: String): Coordinates = {
    xy.split(",") match {
      case pair: Array[String] => (pair(0).toInt, pair(1).init.toInt)
      case _ => (0, 0)
    }
  }

  // width, height
  def parseArea(s: String) : (Int, Int) = {
    s.split("x").map(_.toInt) match {
      case a: Array[Int] => (a(0), a(1))
      case _ => (0, 0)
    }
  }

  // FIXME: should return set
  def findOverlap(r1: Rectangle, r2: Rectangle): HashSet[Coordinates] = r1.points intersect r2.points

  def isOverLap(r1: Rectangle, r2: Rectangle): Boolean = {
    //println(r1, r2)
    // if they overlap it will produce a rectangle
    if ((r1.br._1 > r2.tl._1 && r1.br._2 > r2.tl._2) || 
      (r2.br._1 > r1.tl._1 && r2.br._2 > r1.tl._2)) {
      true
    } else false
  }
  def findOverLapFuture(ls: List[Rectangle]) = Future {
    findOverlap(ls(0), ls(1))
  }
}

object Day3Obj extends Day3 {
  /*
   * find all the overlaps
   * order does not matter
   * */


  val lines = Source.fromResource("d3.txt").getLines.toList
  var rectangles = new ListBuffer[Rectangle]()
  lines.foreach(line => {
    val tokens = line.split(" ")
    val coordinates = parseCoordinates(tokens(2))
    val wh = parseArea(tokens(3))
    //println(coordinates, wh)
    val rect = Rectangle(coordinates, (coordinates._1 + wh._1 - 1, coordinates._2 + wh._2 - 1))
    rectangles += rect
  })
  implicit val highlyParallelExecutionContext = ExecutionContext.fromExecutor(new ForkJoinPool(20))

  val ans = rectangles.toList.combinations(2)
    .flatMap(p => findOverlap(p(0), p(1)))
    .toSet
    .size
  println(ans)
  //
  //val futures = Future.traverse(rectangles.toList.combinations(2)) { findOverLapFuture(_) }
  //futures.onComplete {
    //case Success(result) => {
      //var hs = new HashSet[Coordinates]
      //result.foldLeft(hs) {(acc, c) => acc union c}
      //println(hs.size)
    //}
  //}

}

object Run extends App {
  // Day1
  // Day2
  Day3Obj
}
