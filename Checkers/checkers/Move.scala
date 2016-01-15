package checkers

/**
 * @author Daniel
 * Stores where a piece jumped from, where a piece jumped to, and places jumped to in between
 */

class Move(val side: Int, val from:(Int,Int), val to:(Int,Int), val between:List[(Int,Int)]) {
  //The "No Move" constructor. I really need to learn Scala's "Maybe" or something similar
  def this(){
    this(0,(-1,-1),(-1,-1),List())
  }
  def this(side: Int, from:(Int,Int), to:(Int,Int)){
    this(side, from, to, List())
  }
  def equals():Boolean = {
    true
  }
  override def toString():String = {
    val str = side + " " + from + " " + to + " " + between
    str
  }
}