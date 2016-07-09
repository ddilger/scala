package checkers

import ailibrary.Side

/**
 * @author Daniel
 * Manages loading games from files, as well as switching sides in the current game and restarting
 */


/* Game format:
 * W[(A, 1), (B, 2), (C, 2), ...], B[(), (), (), ...]
 * 
 */
object GameLoader {
  def startGame(player_side: Int):Unit = {
    GameManager.current_game = new CheckersGame()
    GameManager.focus_on = false
    GameManager.focus = (-1,-1) 
    GameManager.current_turn = "user" 
  
  var ai = new AI(GameManager.current_game.ai_side)
  }
  
  def letterToCol(letter: Char):Int = {
    if(letter == 'A'){
      7
    }
    else if(letter == 'B'){
      6
    }
    else if(letter == 'C'){
      5
    }
    else if(letter == 'D'){
      4
    }
    else if(letter == 'E'){
      3
    }
    else if(letter == 'F'){
      2
    }
    else if(letter == 'G'){
      1
    }
    else if(letter == 'H'){
      0
    }
    else
    {
      -1 //something went wrong
    }
  }
  
  def numToRow(num: Char):Int = {
    if(num == '1'){
      0
    }
    else if(num == '2'){
      1
    }
    else if(num == '3'){
      2
    }
    else if(num == '4'){
      3
    }
    else if(num == '5'){
      4
    }
    else if(num == '6'){
      5
    }
    else if(num == '7'){
      6
    }
    else if(num == '8')
    {
      7
    }
    else
    {
      -1 //something went wrong
    }
  }
  
  def produceCellGrid(s: String):Array[Array[CheckersPiece]] = {
    //Separate the list of white pieces from the list of black
    val white_string = "W[(K, A, 1), (R, B, 2)]"
    val white_tuple_strings = List("(K, A, 1)")
    //produce the white tuples
    //val white_tuples = List((Side.White, 'K', letterToCol('B'), numToRow('1')))
    val white_tuples = for{
      next_string <- white_tuple_strings
    } yield
      //hardcoding the character locations is not the most elegant!
      (Side.White, next_string(1), letterToCol(next_string(4)), numToRow(next_string(7)))
    
    
    //produce the black tuples
    
    //concatenate the two lists into a single list of tuples
    val all_tuples = white_tuples
    
    //finally, produce the cell grid
    var cell_grid = for {
      i <- Array.range(0,  8)
    } yield for {
      j <- Array.range(0,  8)
        
      } yield (new CheckersPiece)
      
      for(next_tuple <- all_tuples){
        val side = next_tuple._1
        val piece_type = next_tuple._2
        val i = next_tuple._3
        val j = next_tuple._4
        cell_grid(i)(j) = if(piece_type == 'K'){
          new King(side, i, j)
        } else {
          new CheckersPiece(side, i, j)
        }
      }
      
      cell_grid
  }
  
  //helper function for file handling
  //the string represents the cell grid
  //the cell grid is used to produce the board
  //the board is used to produce the checkers game
  def loadGame(s: String):CheckersGame = {
    var g = new CheckersGame
    g.board = new Board(this.produceCellGrid(s))
    g
  }
  //loads, from a file, a string representing a game
  def loadGame():Unit = {
    
  }
  
  def saveGame():Unit = {
    println(GameManager.current_game.toString())
  }
}