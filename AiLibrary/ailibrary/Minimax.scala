package ailibrary

/**
 * @author Daniel
 */
object Minimax {
  //A more advanced version could allow greater depth if the tree isn't very wide
  //That would require another parameter
   def minimax(startNode: GameNode, curPlayer: Player, depth: Int): Int = {
    val possibleMoves = startNode.nextNodes(curPlayer)
    if (depth == 0 || possibleMoves.isEmpty) {
      startNode.value(curPlayer)
    }
    else
    {
      val maxes = possibleMoves.map(b => minimax(b, curPlayer.opponent(), depth-1))
      maxes.reduceLeft(_ min _)
    }
  }
   //If no depth specified, use an arbitrarily large number 
  def minimax(startNode: GameNode, curPlayer: Player): Int = {
     minimax(startNode, curPlayer, 1000)
   }
}