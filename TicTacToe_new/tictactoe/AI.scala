package tictactoe

import ailibrary._

/**
 * @author Daniel
 */
class AI (val side: Int) extends Player{
  //minimax returns the value of a certain board from a given side's perspective
  /*
  def minimax(startBoard: Board, curPlayer: Int): Int = {
    val possibleMoves = startBoard.nextBoards(curPlayer)
    if (possibleMoves.isEmpty) {
      println("val: " + startBoard.boardValue(curPlayer))
      startBoard.boardValue(curPlayer)
    }
    else
    {
      val maxes = possibleMoves.map(b => -minimax(b, TicTacToeGame.oppositeSide(curPlayer)))
      maxes.reduceLeft(_ min _)
    }
  }
  def move(curBoard: Board): (Int, Int, Int) = {
    val possibleMoves = curBoard.nextBoards(side)
//    val valued_choices = possibleMoves.map(b => (b, minimax(b, side)))
    val valued_choices = possibleMoves.map(b => (b, minimax(b, TicTacToeGame.oppositeSide(side))))
    def max(t1:(Board, Int), t2:(Board, Int)): (Board, Int) = if(t1._2 > t2._2) t1 else t2
    val best_choice = valued_choices.reduceLeft(max)
    if(possibleMoves.length > 0){
      best_choice._1.last_move
    //val decision = possibleMoves(0)
    //decision.last_move
    }
    else{
      (0,-1,-1) //some invalid move
    }
  }
  * */
  def opponent(): Player = {
    if(side == Side.White){
      WhitePlayer.opponent
    }
    else{
      WhitePlayer
    }
  }
  def bestMove(board: Board):Board = {
    val best_choice = ZeroSum.minimax_decision(board, this)
    if(best_choice == null){
      null
    }
    else{
     best_choice.asInstanceOf[Board] 
    }
  }
  def move(curBoard: Board): (Int, Int, Int) = {
    val best_choice = bestMove(curBoard)
    if(best_choice == null){
      (0, -1, -1) //some invalid move
    }
    else{
    //  println("side: " + side + " best choice value: "+best_choice.value(this))
      best_choice.last_move
    }
  }
}