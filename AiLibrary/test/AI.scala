package test

import ailibrary._

/**
 * @author Daniel
 */
class AI(side: Int, opponent: Player) extends Player{
  def this(new_side: Int) = { 
    this(new_side, (if (new_side == Side.White) BlackPlayer else WhitePlayer))
  }
  
  def side():Int = {
    side
  }
  def opponent():Player = {
    opponent
  }
  def bestMoveVal(board: Board):Int = {
    ZeroSum.minimax_value(board, this)
  }
  def bestMove(board: Board):Board = {
    val best_choice = ZeroSum.minimax_decision(board, this)
    if(best_choice == null){
      null
    }
    else{
     best_choice.asInstanceOf[Board] 
    }
    /*
    val possibleMoves = board.nextNodes(this)
    if(possibleMoves.isEmpty){
       null 
    }
    else{
      
    val valued_choices = possibleMoves.map(b => (b, ZeroSum.minimax_value(b, this)))
    println("number of valued choices: " + valued_choices.length)
    println(valued_choices)
    def max(t1:(GameNode, Int), t2:(GameNode, Int)): (GameNode, Int) = if(t1._2 > t2._2) t1 else t2
    val best_choice = valued_choices.reduceLeft(max)
    (best_choice._1).asInstanceOf[Board]
    
    } */
  }
}