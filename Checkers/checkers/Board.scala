package checkers

import ailibrary._

/**
 * @author Daniel
 */
class Board(val cell_grid: Array[Array[CheckersPiece]], val last_move: Move) extends GameNode{
  def this(){
     this(for {
      i <- Array.range(0,  8)
    } yield for {
      j <- Array.range(0,  8)
        
      } yield (
          if (j == 3 || j == 4){
            new CheckersPiece
          }
          else{
            if(j < 3){
              if (i % 2 == 0 ^ j % 2 == 1)
                new CheckersPiece
              else new CheckersPiece(Side.Black,i,j)
            }
            else if(j > 4){
              if (i % 2 == 0 ^ j % 2 == 1)
                new CheckersPiece
              else new CheckersPiece(Side.White,i,j)
              //else new King(Side.White,i,j)
            } 
            else {new CheckersPiece}
          }
            )
    , new Move())
    }
  //Is this function necessary?
    def piecesOfSide(side: Int): List[CheckersPiece] = {
      val side_grid = for{i <- Array.range(0,8)} 
        yield for{j <- Array.range(0,8) if cell_grid(i)(j).side == side} yield cell_grid(i)(j)
      side_grid.flatten.toList
    }
    //Only used by CheckersPiece
    def inBounds(x:Int, y:Int):Boolean = {
       if(x < 0 || y < 0){
        false
      } else if(x > 7 || y > 7){
        false
      } else {
       true 
      }
    }
    def pieceAt(i:Int, j:Int):CheckersPiece = {
      cell_grid(i)(j)
    }
    def pieceAt(c:(Int,Int)):CheckersPiece = {
      pieceAt(c._1, c._2)
    }
    
    //getMove is a temporary fix for user input. It does not resolve ambiguity about jump paths
    def getMove(moving_side: Int, from: (Int,Int), to: (Int,Int)):Move = {
      if(cell_grid(from._1)(from._2).side != moving_side){
        null
      }
      else{
        val moves = cell_grid(from._1)(from._2).moves(this) 
        val matching_moves = for{m <- moves if m.side==moving_side && m.from==from && m.to == to} yield m
        if (matching_moves.length == 0){
          null
        } else {
          matching_moves(0)
        }
      }
  }
    def value(player: Player): Int = {
        val player_pieces = piecesOfSide(player.side)
        val opponent_pieces = piecesOfSide(player.opponent.side)
        if(player_pieces == 0){
          -1000 //Game lost
        }
        else if(opponent_pieces == 0){
          1000 //Game won
        }
        else{
          player_pieces.length - opponent_pieces.length //perhaps there's a more intelligent base valuation...
        }
    }
     //How best to build a 1-d list from a 2-d for comprehension? flatmap?
    // For now, I'll just do what I did in tic-tac-toe...
    def nextNodes(player: Player): List[Board] = {
      //list all pieces of player's side
      val player_pieces = (for{i <- Array.range(0,7)} yield for{j <- Array.range(0,7)} yield cell_grid(i)(j)).flatten.filter(p => p.side == player.side)
      //make a list out of all possible moves of all these pieces
      val possible_moves = (for{p<-player_pieces} yield p.moves(this)).flatten
      //for each element in the possible moves list, generate a board
      for{m <- possible_moves.toList} yield (
          updatedBoard(player.side, m.from, m.to, m.between)
        )
      }
    def updatedBoard(side: Int, from: (Int, Int), to: (Int, Int), jumped_over:List[(Int,Int)]):Board = {
      val new_grid = for{
        i <- Array.range(0,8)
      } yield for{
        j <- Array.range(0,8)
      } yield (
          if((i,j) == to){
            cell_grid(from._1)(from._2).movedTo(to._1, to._2)
          }
          else if((i,j) == from){
            new CheckersPiece            
          }
          else{
            if(jumped_over.contains((i,j)))
              new CheckersPiece
            else
              cell_grid(i)(j).copy //deep copy needed because the same board gets updated many times
            }
          )
      new Board(new_grid, new Move(side, from, to, jumped_over))
    }
    def updatedBoard(side: Int, from: (Int, Int), to: (Int, Int)):Board = {
      updatedBoard(side,from,to,List[(Int,Int)]())
    }

    def possibleMoves(side: Int, x: Int, y: Int):List[Move] = {
      cell_grid(x)(y).moves(this)
    }
    override def toString():String = {
      var str = ""
      str += "\n"
      str += "White pieces: \n"
      for(s <- piecesOfSide(Side.White))
        str += s
      str += "\n"
      str += "Black pieces: \n"
      for(s <- piecesOfSide(Side.Black))
        str += s
      str += "\n"
      str += "Last move: " + last_move.toString + "\n"
      str
    }
}