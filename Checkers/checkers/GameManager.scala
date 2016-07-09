package checkers

/**
 * @author Daniel
 * GameManger integrates details from the game, details about the AI and user's sides,
 * and GUI details
 */
object GameManager {
  val board_dimension = 400
  val square_width = (board_dimension / 8).toInt
  
 // var current_game = new CheckersGame()
  var current_game = GameLoader.loadGame("")
  println(current_game.board)
  var focus_on = false
  //I don't like the use of an "invalid tuple" here...
  var focus = (-1,-1) //no focus anywhere on board (value at the start of each turn)
  var current_turn = "user" //making this a string is rather ugly...
  
  var ai = new AI(current_game.ai_side)
  
  //Switches the user and AI's side. Not sure whether to do it here or in GameLoader
  def switchSides(): Unit = {
    
  }
  
  def updateFocus(x: Int, y: Int): Unit = {
    if(current_turn == "user" && current_game.user_side == current_game.board.cell_grid(x)(y).side){
      focus_on = true
      focus = (x,y)
    }
    else{
      focus_on = false
    }
  }
  
  //moves the ai
  def processAIMove():Unit = {
    current_game.board = ai.move(current_game.board)
    current_turn = "user"
  }
  
  //moves the piece the user last focused on (assuming it's the user's turn)
  def processUserMove(x: Int, y: Int):Unit = {
    if(current_turn == "user" && !focus_on && focus._1 != -1){
      if(current_game.canMove(current_game.user_side, (focus),(x,y))){
        current_game.movePiece(focus, (x, y))
        current_turn = "ai"
      }
    }
  }
}