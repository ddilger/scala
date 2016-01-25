package snake

/**
 * @author Daniel
 */

 import scala.swing._
 import scala.swing.event._
 import java.awt.Color
 
 
class MyPanel extends Panel{
  opaque = true
  background = Color.black
  var board = new Board()
  var x_move = 1
  var y_move = 0
  var last_x_move = 0
  var last_y_move = 0
  var score = board.snake.body.length.toString
  
  //var player = new ComputerPlayer(board)
  
  override def paintComponent(g: java.awt.Graphics2D) {
   listenTo(keys)
   //These if statements look pretty ugly
   //they should be referring to "neck" but can't because the list
   //doesn't necessarily stay in order
    reactions += {
      case KeyPressed(_, Key.R, _, _) =>
         board = new Board()
         x_move = 1
         y_move = 0
         score = board.snake.body.length.toString
      case KeyPressed(_, Key.Up, _, _) =>
        //if(score == 1 || board.snake.body.contains(board.snake.head._1, board.snake.head._2-1)) {
        //if(true){
        if(board.snake.body.length == 1 || last_y_move != 1) {
          y_move = -1
          x_move = 0
        }
      case KeyPressed(_, Key.Down, _, _) =>
        //if(y_move != -1) {
        //if(score == 1 || board.snake.body.contains(board.snake.head._1, board.snake.head._2+1)) {
        //if(true){
        if(board.snake.body.length == 1 || last_y_move != -1) {
          y_move = 1
          x_move = 0
        }
      case KeyPressed(_, Key.Left, _, _) =>
        //if (board.snake.neck == (board.snake.head._1, board.snake.head._2+1)) {
        //if(!board.snake.goingBackwards()) {
        if(board.snake.body.length == 1 || last_x_move != 1) {
          x_move = -1
          y_move = 0
        }
      case KeyPressed(_, Key.Right, _, _) =>
        //if(score == 1 || board.snake.body.contains(board.snake.head._1-1, board.snake.head._2)) {
        if(board.snake.body.length == 1 || last_x_move != -1) {
        //if(true){
          x_move = 1
          y_move = 0
        }
  }
   focusable = true
   requestFocus
//During updateBoard, need to check if it eats itself
   if(!board.gameOver)
     board = board.updateBoard(x_move, y_move)
   score = board.snake.body.length.toString
   
   last_x_move = x_move
   last_y_move = y_move
   
    super.paintComponent(g)
    
      //Draw the snake
    g.setColor(Color.green)
    
    for (i <- 0 to 29){
      //for (j <- 0 to 29 if board.snake.body.contains((i,j))){
      for (j <- 0 to 29 if board.snakeCell((i,j))){
        g.fillRect(i*10, j*10, 10, 10)
        
        //draw the outline of each snake cell
        g.setColor(Color.black)
        g.drawRect(i*10, j*10, 10, 10)
        g.setColor(Color.green)
      }
    }
   
   //Draw the board
    
    g.setColor(Color.red)
    g.drawString(score, 20, 325)
    g.drawString("Press 'R' to restart", 150, 325)
    if(board.gameOver){
     g.drawString("Game Over!", 50, 325)
   }
   
    for (i <- 0 to 29){
      for (j <- 0 to 29 if board.cell_grid(i)(j) == 1){
        g.fillRect(i*10, j*10, 10, 10)
        
        //draw the outline of each food block
        g.setColor(Color.black)
        g.drawRect(i*10, j*10, 10, 10)
        g.setColor(Color.red)
      }
    }
    
    
  
    
    
    //val (x_move, y_move) = player.moveDecision()
//    board = board.updateBoard(x_move, y_move)
    //player = player.updatePlayer(board)
    Thread.sleep(275)
    repaint
  }
}