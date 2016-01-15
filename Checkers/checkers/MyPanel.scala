package checkers

import ailibrary._

 import scala.swing._
 import scala.swing.event._
 import java.awt.Color

/**
 * @author Daniel
 * MyPanel displays the game and takes user interaction. It communicates mainly with GameManager
 */
class MyPanel  extends Panel{
  opaque = true
  background = Color.white
  
  def rowClicked(p: Point): Int = {
    val y1 = math.floor(p.getY()/GameManager.square_width)
    val y2 = y1.toInt
    //y2 - 1
    y2
  }
  def colClicked(p: Point):Int = {
    math.floor(p.getX()/GameManager.square_width) toInt
  }
  
  def inBounds(p: Point):Boolean = {
    p.getX() < GameManager.board_dimension && p.getY() < GameManager.board_dimension
  }
  
  override def paintComponent(g: java.awt.Graphics2D) {
       listenTo(mouse.clicks)
       reactions += {
         case e: MouseClicked =>
           val click_row = rowClicked(e.point) 
           val click_col = colClicked(e.point)
           if(inBounds(e.point)){
             GameManager.updateFocus(click_col, click_row)
             GameManager.processUserMove(click_col, click_row)
             //Also needs to check if the user clicked one of the buttons
           }
           else{
             GameLoader.saveGame()
           }
       }
       super.paintComponent(g)
       
       //shorthand for values from GameManager
       val b = GameManager.current_game.board
       val s = GameManager.square_width
       
       /* Draw the buttons the user could click */
       /* This code demonstrates how I need to use more swing components than Panel */
       g.setColor(Color.black)
       g.drawRect(405, 50, 72, 25)
       g.setColor(Color.gray)
       g.fillRect(406, 51, 71, 24)
       g.setColor(Color.black)
       g.drawString("New Game", 406, 65)
       
       g.setColor(Color.black)
       g.drawRect(405, 80, 72, 25)
       g.setColor(Color.gray)
       g.fillRect(406, 81, 71, 24)
       g.setColor(Color.black)
       g.drawString("Switch sides", 406, 95)
       
       g.setColor(Color.black)
       g.drawRect(405, 110, 72, 25)
       g.setColor(Color.gray)
       g.fillRect(406, 111, 71, 24)
       g.setColor(Color.black)
       g.drawString("Save game", 406, 125)
       
       g.setColor(Color.black)
       g.drawRect(405, 140, 72, 25)
       g.setColor(Color.gray)
       g.fillRect(406, 141, 71, 24)
       g.setColor(Color.black)
       g.drawString("Load game", 406, 155)
       
       /* Draw the board */
       //Black squares and red squares

       for (i <- 0 to 7){
        for (j <- 0 to 7){
          val s = GameManager.square_width
          if(i % 2 == 0 ^ j % 2 == 1){
            g.setColor(Color.red)
            g.fillRect(i*s, j*s, s, s)
          }
          else{
            g.setColor(Color.black)
            g.fillRect(i*s, j*s, s, s)
          }
        } 
      }
      /* Draw the pieces */
      //White for the white pieces, gray for the black pieces
        for (i <- 0 to 7){
         for (j <- 0 to 7){
            if(b.cell_grid(i)(j).side == Side.White){
              g.setColor(Color.white)
              g.fillOval(i*s + s/4, j*s + s/4, s/2, s/2)             
            }
            else if(b.cell_grid(i)(j).side == Side.Black){
              g.setColor(Color.gray) //Need a better substitute color for black
              g.fillOval(i*s + s/4, j*s + s/4, s/2, s/2)              
            }
            //mark kings with a "k"
           //if(b.cell_grid(i)(j).isInstanceOf[King]){
            if(b.cell_grid(i)(j).kinged){
              g.setColor(Color.blue)
              g.drawString("k", i*s + s/2, j*s + s/2)
            }
           }
         }
        
        /* Highlight the piece the user chose to focus on */
        if(GameManager.focus_on){
          g.setColor(Color.yellow)
          g.drawRect(s*GameManager.focus._1, s*GameManager.focus._2, s, s)
        }
        
        /* Draw any moves the highlighted piece might be able to make */
        if(GameManager.focus_on){
          val x = GameManager.focus._1
          val y = GameManager.focus._2
          val highlighted_piece = GameManager.current_game.board.cell_grid(x)(y)
          val possible_moves = highlighted_piece.moves(GameManager.current_game.board)
          for(m <- possible_moves){
            val dest_square = m.to
            val dest_x = dest_square._1
            val dest_y = dest_square._2 
            g.fillRect(s*dest_x, s*dest_y, s, s)
          }
        }
        
       /* Refresh the board */
       Thread.sleep(25)
       repaint
       
       /* Move the AI */
       if(GameManager.current_turn == "ai"){
          Thread.sleep(25)
          GameManager.processAIMove()
          repaint
        }
   }
}