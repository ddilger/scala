package ailibrary

/**
 * @author Daniel
 */
object ZeroSum {
  /* Negamax */
  //A more advanced version could allow greater depth for when the tree isn't very wide
  //That would require another parameter
   def negamax_value(startNode: GameNode, curPlayer: Player, depth: Int): Int = {
    val possibleMoves = startNode.nextNodes(curPlayer)
   // println("possible moves length: " + possibleMoves.length)
    if (depth == 0 || possibleMoves.isEmpty) {
   //  println("curPlayer side: "+curPlayer.side+" board value: "+startNode.value(curPlayer) + " board: "+startNode.toString)
      startNode.value(curPlayer)
    }
    else {
    //  val maxes = possibleMoves.map(b => minimax_value(b, curPlayer.opponent(), depth-1))
      val maxes = possibleMoves.map(b => -negamax_value(b, curPlayer.opponent(), depth-1))
      //println(maxes + " " + possibleMoves + " " + maxes.reduceLeft(_ max _))
    //  maxes.reduceLeft(_ min _)
        maxes.reduceLeft(_ max _)
    }
  }
   //If no depth specified, use an arbitrarily large number 
   def negamax_value(startNode: GameNode, curPlayer: Player): Int = {
     negamax_value(startNode, curPlayer, 1000)
   }
  def negamax_decision(startNode: GameNode, curPlayer: Player, depth: Int): GameNode = {
    val possibleMoves = startNode.nextNodes(curPlayer)
    if(possibleMoves.isEmpty){
       null 
    }
    else {
      val valued_choices = possibleMoves.map(n => (n, -ZeroSum.negamax_value(n, curPlayer.opponent, depth)))
      //println("Thus: "+valued_choices.map(n => n._2))
      def max(t1:(GameNode, Int), t2:(GameNode, Int)): (GameNode, Int) = if(t1._2 > t2._2) t1 else t2
      val best_choice = valued_choices.reduceLeft(max)
     // val best_choice = valued_choices.last
      best_choice._1
      }
  }
  
  def negamax_decision(startNode: GameNode, curPlayer: Player): GameNode = {
      //println("decision made")
   //   negamax_decision(startNode, curPlayer, 2)//Needs to be an arbitrarily large number, not 2
    negamax_decision(startNode, curPlayer, 1000)
    }
  
    /* Minimax */
  /*
   def minimax_value(startNode: GameNode, maxPlayer: Player, maxTurn: Boolean, depth: Int): Int = {
    val curPlayer = if (maxTurn) {maxPlayer} 
      else {maxPlayer.opponent}
    val possibleMoves = startNode.nextNodes(curPlayer)
    //Terminal test
    if (depth == 0 || possibleMoves.isEmpty) {
      startNode.value(maxPlayer)
    }
    //Max is to move in state
    else if (maxTurn){
      val maxes = possibleMoves.map(b => minimax_value(b, maxPlayer, false, depth-1))
      maxes.reduceLeft(_ max _)   
    }
    //Min is to move in state
    else {
      val mins = possibleMoves.map(b => minimax_value(b, maxPlayer, true, depth-1))
      mins.reduceLeft(_ min _)
    }
  }
   //If no depth specified, use an arbitrarily large number 
   def minimax_value(startNode: GameNode, curPlayer: Player): Int = {
     minimax_value(startNode, curPlayer, true, 1000)
   }
  def minimax_decision(startNode: GameNode, curPlayer: Player, depth: Int): GameNode = {
    val possibleMoves = startNode.nextNodes(curPlayer)
    if(possibleMoves.isEmpty){
       null 
    }
    else {
      val valued_choices = possibleMoves.map(n => (n, ZeroSum.minimax_value(n, curPlayer, false, depth)))
      //println("valued choices: "+valued_choices)
      def max(t1:(GameNode, Int), t2:(GameNode, Int)): (GameNode, Int) = if(t1._2 > t2._2) t1 else t2
      val best_choice = valued_choices.reduceLeft(max) //a (choice, value) pair
      //println("Best choice value: "+best_choice._2)
      best_choice._1
      }
  }
  def minimax_decision(startNode: GameNode, curPlayer: Player): GameNode = {
    minimax_decision(startNode, curPlayer, 1000)
    }
  */
    /* Alpha Beta pruning */
  def alphabeta_value(startNode: GameNode, maxPlayer: Player, maxTurn: Boolean, alpha: Int, beta: Int, depth: Int): Int = {
    val curPlayer = if (maxTurn) {maxPlayer} 
      else {maxPlayer.opponent}
    val possibleMoves = startNode.nextNodes(curPlayer)
    //Terminal test
    if (depth == 0 || possibleMoves.isEmpty) {
      startNode.value(maxPlayer)
    }
    //Max-Value
    else if (maxTurn){
      def max_value(a: Int, moves: List[GameNode]): Int = {
        //println("moves: "+moves)
        if (moves.isEmpty){
          a
        }
        else{
            val min_val = alphabeta_value(moves.head, maxPlayer, false, a, beta, depth-1)
            val new_alpha = if(a > min_val){a} else {min_val}
            if(new_alpha >= beta){
              beta
            }
            else {
              
              max_value(new_alpha, moves.tail)
            }
          }
        }
      max_value(alpha, possibleMoves)
    }
    //Min-Value
    else {
       def min_value(b: Int, moves: List[GameNode]): Int = {
        if (moves.isEmpty){
          b
        }
        else{
            val max_val = alphabeta_value(moves.head, maxPlayer, true, alpha, b, depth-1)
            val new_beta = if(b < max_val){b} else {max_val}
            if(new_beta <= alpha){
              alpha
            }
            else {
              min_value(new_beta, moves.tail)
            }
          }
        }
      min_value(beta, possibleMoves)
    }
  }
   //If no depth specified, use an arbitrarily large number 
   def alphabeta_value(startNode: GameNode, curPlayer: Player): Int = {
     alphabeta_value(startNode, curPlayer, true, -1000, 1000, 1000)
   }
  def alphabeta_decision(startNode: GameNode, curPlayer: Player, depth: Int): GameNode = {
    val possibleMoves = startNode.nextNodes(curPlayer)
    if(possibleMoves.isEmpty){
       null 
    }
    else {
      val valued_choices = possibleMoves.map(n => (n, ZeroSum.alphabeta_value(n, curPlayer, false, -1000, 1000, depth)))
      //println("valued choices: "+valued_choices)
      def max(t1:(GameNode, Int), t2:(GameNode, Int)): (GameNode, Int) = if(t1._2 > t2._2) t1 else t2
      val best_choice = valued_choices.reduceLeft(max) //a (choice, value) pair
      //println("Best choice value: "+best_choice._2)
      best_choice._1
      }
  }
  def alphabeta_decision(startNode: GameNode, curPlayer: Player): GameNode = {
    alphabeta_decision(startNode, curPlayer, 1000)
    }
}