package ailibrary

/**
 * @author Daniel
 */
object ZeroSum {
  //A more advanced version could allow greater depth for when the tree isn't very wide
  //That would require another parameter
   def minimax_value(startNode: GameNode, curPlayer: Player, depth: Int): Int = {
    val possibleMoves = startNode.nextNodes(curPlayer)
   // println("possible moves length: " + possibleMoves.length)
    if (depth == 0 || possibleMoves.isEmpty) {
   //  println("curPlayer side: "+curPlayer.side+" board value: "+startNode.value(curPlayer) + " board: "+startNode.toString)
      startNode.value(curPlayer)
    }
    else {
    //  val maxes = possibleMoves.map(b => minimax_value(b, curPlayer.opponent(), depth-1))
      val maxes = possibleMoves.map(b => -minimax_value(b, curPlayer.opponent(), depth-1))
      //println(maxes + " " + possibleMoves + " " + maxes.reduceLeft(_ max _))
    //  maxes.reduceLeft(_ min _)
        maxes.reduceLeft(_ max _)
    }
  }
   //If no depth specified, use an arbitrarily large number 
   def minimax_value(startNode: GameNode, curPlayer: Player): Int = {
     minimax_value(startNode, curPlayer, 1000)
   }
  def minimax_decision(startNode: GameNode, curPlayer: Player, depth: Int): GameNode = {
    val possibleMoves = startNode.nextNodes(curPlayer)
    if(possibleMoves.isEmpty){
       null 
    }
    else {
      val valued_choices = possibleMoves.map(n => (n, -ZeroSum.minimax_value(n, curPlayer.opponent, depth)))
      //println("Thus: "+valued_choices.map(n => n._2))
      def max(t1:(GameNode, Int), t2:(GameNode, Int)): (GameNode, Int) = if(t1._2 > t2._2) t1 else t2
      val best_choice = valued_choices.reduceLeft(max)
     // val best_choice = valued_choices.last
      best_choice._1
      }
  }
  def minimax_decision(startNode: GameNode, curPlayer: Player): GameNode = {
      //println("decision made")
   //   minimax_decision(startNode, curPlayer, 2)//Needs to be an arbitrarily large number, not 2
    minimax_decision(startNode, curPlayer, 1000)
    }
}