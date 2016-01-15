package tictactoe

import scala.collection.mutable.Stack
import ailibrary._

/**
 * @author Daniel
 */
//This game looks increasingly like a class in itself.
//I should still have an object to hold constants, but states such as player side and victor should be in a class
object TicTacToeGame {
  
  val cell_dimension = 100 //Each square is 75x75
  var board = new Board()
  //var opponentsTurn = false
  var playerSide = 1
  var opponentSide = 2
  var curTurn = 1
  var victor = -1
  var history = Stack[Board]()
  var opponent = new AI(opponentSide)
  var started = false
  //var started = true
  
  def oppositeSide(side: Int) = {
    if(side == 1)
      2
    else
      1
  }
  
  def beginGame() = {
    //println("Begin game")
    started = true
  }
  
  def switchSides() = {
    playerSide = oppositeSide(playerSide)
    opponentSide = oppositeSide(opponentSide)
    opponent = new AI(opponentSide)
  }
  
  def takeBackMove(): Boolean = {
    if(history.length < 2)
    {
      false //can't reverse a move if none have been made
      //Always an even number of moves, because the AI makes its move immediately
    }
    else
    {
      history.pop() //Remove last AI move
      board = history.pop() //Go back to the board before player's last move
      true
    }
  }
  
  def determineVictor() = {
     //determine the victor, if there is one
    if(board.value(WhitePlayer) == 1){
      victor = 1
    }
    else if(board.value(WhitePlayer.opponent) == 1){
      victor = 2
    }
    else if(board.nextNodes(WhitePlayer).length == 0 && board.nextNodes(WhitePlayer.opponent).length == 0){
      victor = 0
    }
  }
  
  def makeAIMove() = {
    val opponentDecision = opponent.move(board)
    val move_x = opponentDecision._2
    val move_y = opponentDecision._3
    if(victor != -1){ //game already decided
    }
    else if(opponentDecision._1 == 0) //no moves possible
    {
      determineVictor()
    }
    else{
      makeMove(opponent.side, move_x, move_y)
    }

  }
  
  def makeMove(side: Int, x: Int, y: Int): Boolean = {
    if(victor != -1){ //game over
      return false //false means no move was made
    }
    if(curTurn != side){
      return false
    }
    if(board.validMove(side, x, y)) {
      history.push(board.copy())
      board = board.updatedBoard(side, x, y)
      //println("board value to white: " + board.value(WhitePlayer))
      //update whose turn it is
      curTurn = oppositeSide(curTurn)
    }
   determineVictor() //see if the game has been decided
    true //true means a move was executed
  }
  
  //Essentially instantiating a new object
  def restart() = {
    board = new Board()
    playerSide = 1
    opponentSide = 2
    victor = -1
    curTurn = 1
    history.clear()
    opponent = new AI(opponentSide)
    started = false
  }
}