package tictactoe

/**
 * @author Daniel
 */

 import scala.swing._
 import scala.swing.event._
 import java.awt.Color
 
 
class MyPanel extends Panel{
  opaque = true
  background = Color.white
  
  def rowClicked(p: Point): Int = {
    val y1 = math.floor(p.getY()/TicTacToeGame.cell_dimension)
    val y2 = y1.toInt
    //y2 - 1
    y2
  }
  def colClicked(p: Point):Int = {
    math.floor(p.getX()/TicTacToeGame.cell_dimension) toInt
  }
   def inBounds(p: Point):Boolean = {
    if ((p.getX() < TicTacToeGame.cell_dimension * 3)
      && (p.getY() < TicTacToeGame.cell_dimension * 3)) {
       true 
    } else {
      false
    }
  }
  
  override def paintComponent(g: java.awt.Graphics2D) {
    listenTo(mouse.clicks)
    listenTo(keys)
    reactions += {
      case e: MouseClicked =>
         val click_row = rowClicked(e.point) 
         val click_col = colClicked(e.point)

         if(inBounds(e.point))
         {
           TicTacToeGame.started = true
           TicTacToeGame.makeMove(TicTacToeGame.playerSide, click_col, click_row)
         }
      case KeyPressed(_, Key.B, _, _) => 
          TicTacToeGame.beginGame()
          repaint
      case KeyPressed(_, Key.R, _, _) => 
          TicTacToeGame.restart()
          repaint
      case KeyPressed(_, Key.S, _, _) =>
          //println("switch sides")
          TicTacToeGame.switchSides()
          repaint
          /*
      case KeyPressed(_, Key.T, _, _) =>
          TicTacToeGame.takeBackMove()
          repaint */
    }
    focusable = true
    requestFocus
    
    super.paintComponent(g)
    
       
    //draw the board itself
      val cell_dimension = TicTacToeGame.cell_dimension
      g.setColor(Color.black) 
      for (i <- 0 to 2){
        for (j <- 0 to 2){
          g.drawRect(i*cell_dimension, j*cell_dimension, cell_dimension, cell_dimension)
        } 
      }
    
      //draw the pieces in the board
      //draw the crosses
      g.setColor(Color.red)
       for (i <- 0 to 2){
        for (j <- 0 to 2){
          if(TicTacToeGame.board.cell_grid(i)(j) == 1)
          {
            g.drawString("X", i*cell_dimension+50, j*cell_dimension+50)
          }
        } 
      }
       
      //draw the noughts
      g.setColor(Color.blue)
       for (i <- 0 to 2){
        for (j <- 0 to 2){
          if(TicTacToeGame.board.cell_grid(i)(j) == 2)
          {
            g.drawString("O", i*cell_dimension+50, j*cell_dimension+50)
          }
        } 
      }
      
      //draw the "restart" option
      g.setColor(Color.black)
      g.drawString("Press 'B' to begin", 0, cell_dimension*3 + 30)
      g.drawString("Press 'R' to restart", 0, cell_dimension*3 + 50)
      g.drawString("Press 'S' to switch sides", 0, cell_dimension*3 + 70)
    //  g.drawString("Press 'T' to take back your last move", 0, cell_dimension*3 + 90)
      
      //Display whether or not the game has started
      if(TicTacToeGame.started){
        g.drawString("Game started", 2*cell_dimension+20, cell_dimension*3 + 30)
      }
      else
      {
        g.drawString("Game hasn't begun", 2*cell_dimension+20, cell_dimension*3 + 30)
      }
      
      if(TicTacToeGame.victor != -1){ //Game over messages
        if(TicTacToeGame.victor == TicTacToeGame.playerSide){
          g.drawString("You win!", 2*cell_dimension+20, cell_dimension*3 + 50)    
        }
        else if(TicTacToeGame.victor == 0){
          g.drawString("Draw!", 2*cell_dimension+20, cell_dimension*3 + 50)    
        }
        else{
          g.drawString("You lose!", 2*cell_dimension+20, cell_dimension*3 + 50)
        }
      }
      else{ //If the game is still going, display whose turn it is
        if(TicTacToeGame.curTurn == 1)
          g.drawString("X's turn", 2*cell_dimension+20, cell_dimension*3 + 50)
        else
          g.drawString("O's turn", 2*cell_dimension+20, cell_dimension*3 + 50)
        if(TicTacToeGame.curTurn == TicTacToeGame.playerSide){
          g.drawString("(your turn)", 2*cell_dimension+70, cell_dimension*3 + 50)
        }
        else
        {
          g.drawString("(AI's turn)", 2*cell_dimension+70, cell_dimension*3 + 50)
        }
      }
       
       Thread.sleep(25)
       repaint
       //Have the opponent move
      Thread.sleep(250)
      if(TicTacToeGame.started && TicTacToeGame.curTurn == TicTacToeGame.opponentSide){
        //println("AI going")
        TicTacToeGame.makeAIMove()
      }
       /*
      else if (TicTacToeGame.opponentSide == 1){
       println("game started: " + TicTacToeGame.started)
       println("AI's turn: " + TicTacToeGame.curTurn == TicTacToeGame.opponentSide)
      } */
  }
  
}