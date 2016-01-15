package checkers

import ailibrary._

/**
 * @author Daniel
 */
class AI(val side: Int) extends Player{
  def opponent(): Player = {
    if(side == Side.White){
      WhitePlayer.opponent
    }
    else{
      WhitePlayer
    }
  }
  
  def bestMove(board: Board):Board = {
    val best_choice = ZeroSum.minimax_decision(board, this, 3)
    if(best_choice == null){
      println("The null pointer exception occurs here")
      null
    }
    else{
     best_choice.asInstanceOf[Board] 
    }
  }
  
  def move(curBoard: Board):Board = {
    bestMove(curBoard)
  }
}