package tictactoe

import ailibrary._

/**
 * @author Daniel
 */
class Board(val cell_grid: Array[Array[Int]], val last_move: (Int, Int, Int)) extends GameNode {
  def this()
  {
    this(for {
      i <- Array.range(0,  3)
    } yield for {
      j <- Array.range(0,  3)
        
      } yield 0
    , (0,0,0))
  }
  
  //there are eight different possible lines
  def containsLineOfSide(side: Int): Boolean = {
    if(cell_grid(0)(0) == side && cell_grid(0)(1) == side && cell_grid(0)(2) == side){
      true
    } else if(cell_grid(1)(0) == side && cell_grid(1)(1) == side && cell_grid(1)(2) == side){
      true
    } else if(cell_grid(2)(0) == side && cell_grid(2)(1) == side && cell_grid(2)(2) == side){
      true
    }  else if(cell_grid(0)(0) == side && cell_grid(1)(0) == side && cell_grid(2)(0) == side){
      true
    } else if(cell_grid(0)(1) == side && cell_grid(1)(1) == side && cell_grid(2)(1) == side){
      true
    } else if(cell_grid(0)(2) == side && cell_grid(1)(2) == side && cell_grid(2)(2) == side){
      true
    } else if(cell_grid(0)(0) == side && cell_grid(1)(1) == side && cell_grid(2)(2) == side){
      true
    } else if(cell_grid(0)(2) == side && cell_grid(1)(1) == side && cell_grid(2)(0) == side){
      true
    } else {
      false
    }
  }
  //1 denotes victory, -1 denotes defeat, 0 denotes draw
  //should I make a distinction between the board value of "nothing" and of a draw?
  //the order of valuation matters, which is very problematic
  //I think it's because the game doesn't understand the concept of game termination
  //that was exactly the problem!
  def value(player: Player): Int = {
    val side = player.side
    
    if(containsLineOfSide(side))
      1
    else if(containsLineOfSide(TicTacToeGame.oppositeSide(side)))
      -1
    else
      0
  }
  def nextNodes(player: Player): List[Board] = {
    val side = player.side
    val coords = (for {i <- Array.range(0,3)} yield for{j <- Array.range(0,3)} yield (i,j)).flatten
    val validCoords = coords.filter(p => cell_grid(p._1)(p._2) == 0)
    val free_spaces = for {
      i <- validCoords.toList
    } yield updatedBoard(side, i._1, i._2)
    if(this.value(player) != 0){ //don't generate moves if the game is already decided
      List()
    }
    else{
      free_spaces
    }
  }
  def copy(): Board = {
    val copy_grid = for {
      i <- Array.range(0,  3)
    } yield for {
      j <- Array.range(0,  3)
        
      } yield cell_grid(i)(j) 
    new Board(copy_grid, last_move)
  }
  def updatedBoard(side: Int, x: Int, y: Int): Board = {
    val new_grid = for {
      i <- Array.range(0,  3)
    } yield for {
      j <- Array.range(0,  3)
        
      } yield (if(i==x && j==y) side else cell_grid(i)(j)) 
    new Board(new_grid, (side, x, y))
  }
  
  //A more general solution is to see if this move is contained in nextBoards
  def validMove(side: Int, x: Int, y: Int): Boolean = {
    cell_grid(x)(y) == 0
  }
  override def toString():String = {
    last_move.toString + " " + cell_grid(last_move._2)(last_move._3)
  }
}