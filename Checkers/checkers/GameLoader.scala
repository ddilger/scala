package checkers

/**
 * @author Daniel
 * Manages loading games from files, as well as switching sides in the current game and restarting
 */
object GameLoader {
  def startGame(player_side: Int):Unit = {
    GameManager.current_game = new CheckersGame()
    GameManager.focus_on = false
    GameManager.focus = (-1,-1) 
    GameManager.current_turn = "user" 
  
  var ai = new AI(GameManager.current_game.ai_side)
  }
  
  //helper function for file handling
  def loadGame(s: String):Unit = {
    
  }
  //loads, from a file, a string representing a game
  def loadGame():Unit = {
    
  }
  
  def saveGame():Unit = {
    println(GameManager.current_game.toString())
  }
}