package test

import ailibrary._

/**
 * @author Daniel
 */
object Main {
  //builds a tree of boards to test minimax
  var board = new Board(1)
  def buildTree0() = { //white will choose 3 (val 3), black will choose 2 (val -2)
    var bc1 = new Board(2)
    var bc2 = new Board(3)
    board = board.addChild(bc1)
    board = board.addChild(bc2)
  }
  def buildTree1() = { //white chooses 3 (val 6), black chooses 3 (val -7)
    var bc1 = new Board(2)
   // println("bc1 depth: "+bc1.depth)
    var bc1c1 = new Board(14)
    var bc1c2 = new Board(5)
    bc1 = bc1.addChild(bc1c1)
    bc1 = bc1.addChild(bc1c2)
   // println("bc1 depth: "+bc1.depth)
    
    var bc2 = new Board(3)
    var bc2c1 = new Board(6)
    var bc2c2 = new Board(7)
    bc2 = bc2.addChild(bc2c1)
    bc2 = bc2.addChild(bc2c2)
    
    board = board.addChild(bc1)
    board = board.addChild(bc2) 
  }
   def buildTree2() = {//white chooses 3 (val 13), black chooses 2 (val -8)

    var bc1 = new Board(2)
    var bc1c1 = new Board(4)
    var bc1c1c1 = new Board(8)
    var bc1c1c2 = new Board(9)
    bc1c1 = bc1c1.addChild(bc1c1c1)
    bc1c1 = bc1c1.addChild(bc1c1c2)
    var bc1c2 = new Board(5)
    var bc1c2c1 = new Board(10)
    var bc1c2c2 = new Board(11)
    bc1c2 = bc1c2.addChild(bc1c1c1)
    bc1c2 = bc1c2.addChild(bc1c1c2)
    bc1 = bc1.addChild(bc1c1)
    bc1 = bc1.addChild(bc1c2)
    
    var bc2 = new Board(3)
    var bc2c1 = new Board(6)
    var bc2c1c1 = new Board(12)
    var bc2c1c2 = new Board(13)
    bc2c1 = bc2c1.addChild(bc2c1c1)
    bc2c1 = bc2c1.addChild(bc2c1c2)
    var bc2c2 = new Board(7)
    var bc2c2c1 = new Board(14)
    var bc2c2c2 = new Board(15)
    bc2c2 = bc2c2.addChild(bc2c2c1)
    bc2c2 = bc2c2.addChild(bc2c2c2)
    bc2 = bc2.addChild(bc2c1)
    bc2 = bc2.addChild(bc2c2)
    
    board = board.addChild(bc1)
    board = board.addChild(bc2) 
  }
  def buildTree3() = { //white chooses 4 (val 11), black chooses 2 (val -7)
    var bc1 = new Board(2)
    var bc1c1 = new Board(5)
    var bc1c2 = new Board(6)
    var bc1c3 = new Board(7)
    bc1 = bc1.addChild(bc1c1)
    bc1 = bc1.addChild(bc1c2)
    bc1 = bc1.addChild(bc1c3)
    
    var bc2 = new Board(3)
    var bc2c1 = new Board(8)
    var bc2c2 = new Board(9)
    var bc2c3 = new Board(10)
    bc2 = bc2.addChild(bc2c1)
    bc2 = bc2.addChild(bc2c2)
    bc2 = bc2.addChild(bc2c3)
    
    var bc3 = new Board(4)
    var bc3c1 = new Board(11)
    var bc3c2 = new Board(12)
    var bc3c3 = new Board(13)
    bc3 = bc3.addChild(bc3c1)
    bc3 = bc3.addChild(bc3c2)
    bc3 = bc3.addChild(bc3c3)
    
    board = board.addChild(bc1)
    board = board.addChild(bc2)
    board = board.addChild(bc3)
  }
  def buildTree4() = { //white will pick 3 (val 4), black will pick 2 (val -2)
    var bc1 = new Board(2)
    var bc2 = new Board(3)
    var bc2c1 = new Board(4)
    var bc2c2 = new Board(5)
    bc2 = bc2.addChild(bc2c1)
    bc2 = bc2.addChild(bc2c2)
    
    board = board.addChild(bc1)
    board = board.addChild(bc2)
  }
  def buildTree5() = { //white will choose 2 (val 0), black will choose 3 (val 1)
    var bc1 = new Board(2)
    var bc1c1 = new Board(1)
    var bc1c2 = new Board(0)
    bc1 = bc1.addChild(bc1c1)
    bc1 = bc1.addChild(bc1c2)
    var bc2 = new Board(3)
    var bc2c1 = new Board(-1)
    var bc2c2 = new Board(-1)
    bc2 = bc2.addChild(bc2c1)
    bc2 = bc2.addChild(bc2c2)
    
    board = board.addChild(bc1)
    board = board.addChild(bc2)
  }
  def main(args: Array[String]): Unit = {
    //val a1 = new AI(Side.White)
    val a1 = new AI(Side.Black)
    buildTree5()
    println("ai side: "+a1.side)
    println("Best move val: " + a1.bestMoveVal(board))
    println("Best move: " + a1.bestMove(board).whiteValue)
    println("Tree depth: "+ board.depth)
  }
}