package collapse

import java.awt.Color

/**
 * @author Daniel
 * The CollapseGame object contains the variables for the game itself, such as the grid
 * This is to separate the elements of the game from the means of representing them.
 * In Snake, on the other hand, the elements of the game are stored directly in the MyPanel class
 */
object CollapseGame {
  val num_colors = 4
  val board_width = 20
  val board_height = 20
  val min_removal = 3 //minimum number of cells that need to be contiguous for a cell to be removed
  val start_height = 6

  //var speed = 500 //speed if game is time-based
  var speed = 50 //speed if game is turn-based
  var board = new Board()
  var next_row = this.newRow()
  var gameOver = false
  
  var click = false
  var click_row = 0
  var click_col = 0
  
  var valid_removal = false
  
  val moveup_distance = 2
  var last_moveup = moveup_distance-1
  
  var score = 0
  
  def cellColor(cell_int: Int) : Color = {
    if (cell_int == 1){
      Color.blue
    }
    else if (cell_int == 2){
      Color.red
    }
    else if (cell_int == 3){
      Color.green
    }
    else if (cell_int == 4){
      Color.orange
    }
    else if (cell_int == 5){
      Color.white
    }
    else if (cell_int == 6){
      Color.pink
    }
    else
    {
      Color.white
    }
  }
  def moveUp() = {
    val l = board.cell_grid.transpose
    val l2 = l(0).filter((i: Int) => i != 0)
    if(!l2.isEmpty)
    {
      gameOver = true
    }
    if(!gameOver)
    { 
      //Surely the below can be simplified in a functional manner
      //board = new Board(board.fallIntoPlace(board.cellsRemoved(click_col, click_row)))
      val cells_removed = board.cellsRemoved(click_col, click_row)
      if(valid_removal)
      {
        board = new Board(board.removeGaps(board.fallIntoPlace(cells_removed)))
        last_moveup = (last_moveup+1) % moveup_distance
        if(last_moveup+1 == moveup_distance)
        //if(true)
        {
          board = board.moveUp()
          next_row = newRow()
        }
      }
      
      
    }
  }
  
  def newRow(): Array[Int] = {
    for {
      i <- Array.range(0,  CollapseGame.board_width)
    } yield scala.util.Random.nextInt(num_colors) + 1
  }
  
  //Absolutely not a functional way to restart
  //Suggests maintaining all state change in a singleton object is not a good idea
  //Perhaps I should just maintain it in a class
  def restart() = {
    board = new Board()
    gameOver = false
    score = 0
    last_moveup = moveup_distance
  }
}