package checkers

import ailibrary.Side

/**
 * @author Daniel
 * CheckersGame manages details about a game of checkers, without regards to the user interface
 */
class CheckersGame {
  var board = new Board()
  var user_side = Side.White
  var ai_side = Side.Black
  
  //I need to initialize these variables based on the user's initial side
  
  def canMove(side: Int, from: (Int, Int), to: (Int, Int)):Boolean = {
    //I want to be able to use "contains" with board.possibleMoves instead of going through 
    //this move_tuples value
    val move_tuples = for{m <- board.possibleMoves(side, from._1, from._2)} yield (m.from, m.to) 
    move_tuples.contains((from, to))
  }
  
  def movePiece(from: (Int, Int), to: (Int, Int)): Unit = {
    val move = board.getMove(user_side, from, to)
    if (move != null){
      board = board.updatedBoard(move.side, move.from, move.to, move.between)
    }
  }
  override def toString():String = {
  var str = "user side: " + user_side + "\n"
  str += "ai side: " + ai_side + "\n"
  str += board.toString
  str
  }
}
