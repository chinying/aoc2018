package aoc18

import scala.collection.mutable.HashMap

class FrequencyTable[T] {
	def freq(items: List[T]): HashMap[T, Int] = {
		var table = new HashMap[T, Int]()
		items.foreach(i => {
			table.get(i) match {
				case Some(x: Int) => table += (i -> (x + 1))
				case None => table += (i -> 1)
			}
		})
	  table
	}
}
