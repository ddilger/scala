package test

import ailibrary._
/**
 * @author Daniel
 */
class Board(val whiteValue: Int, children: List[Board]) extends GameNode{
  def this(whiteVal: Int) = {
    this(whiteVal, List())
  }
  def addChild(b: Board): Board = {
    return new Board(whiteValue, children :+ b)
  }
  def value(player: Player): Int = {
    if(player.side == Side.White){
      whiteValue
    } else {
     // println("black's perspective used")
      -whiteValue
    }
  }
  def depth():Int = {
    if(children.isEmpty){
      1
    }
    else{
      1+children(0).depth
    }
  }
  def nextNodes(side: Player): List[GameNode] = {
    //println("next nodes")
    children
  }
  override def toString():String = {
    whiteValue.toString
  }
}