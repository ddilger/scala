package collapse

/**
 * @author Daniel
 */

 import scala.swing._
 import scala.swing.event._
 import java.awt.Color
 
 
class MyPanel extends Panel{
  opaque = true
  background = Color.black
  val cell_width = 20
  
  def rowClicked(p: Point): Int = {
    val y1 = math.floor(p.getY()/cell_width)
    val y2 = y1.toInt
    //y2 - 1
    y2
  }
  def colClicked(p: Point):Int = {
    math.floor(p.getX()/cell_width) toInt
  }
  
  def inBounds(p: Point):Boolean = {
    if ((p.getX() < cell_width * CollapseGame.board_width)
      && (p.getY() < cell_width * CollapseGame.board_height)) {
       true 
    } else {
      false
    }
  }
  
  override def paintComponent(g: java.awt.Graphics2D) {
   listenTo(keys)
   listenTo(mouse.clicks)
   reactions += {
     case KeyPressed(_, Key.R, _, _) => 
       CollapseGame.restart()
       repaint
     case e: MouseClicked =>
       CollapseGame.click_row = rowClicked(e.point) 
       CollapseGame.click_col = colClicked(e.point)
       CollapseGame.click = inBounds(e.point)

   }
   focusable = true
   requestFocus
    
   super.paintComponent(g)
   
   if(CollapseGame.click){
     
   }
   
   //Draw the blocks on the board
   for (i <- 0 to CollapseGame.board_width - 1 ){
      for (j <- 0 to CollapseGame.board_height - 1 if CollapseGame.board.cell_grid(i)(j) != 0){
        val cur_cell_val = CollapseGame.board.cell_grid(i)(j)
        g.setColor(CollapseGame.cellColor(cur_cell_val))
        g.fillRect(i*cell_width, j*cell_width, cell_width, cell_width)
        
        //Now draw the border of the block
        g.setColor(Color.black)
        g.drawRect(i*cell_width, j*cell_width, cell_width, cell_width)
      }
    }
   //Draw the preview of the next row
   for (i <- 0 to CollapseGame.board_width - 1 ){
        val row_location = CollapseGame.board_height + 1
        val cur_cell_val = CollapseGame.next_row(i)
        g.setColor(CollapseGame.cellColor(cur_cell_val))
        g.fillRect(i*cell_width, row_location*cell_width, cell_width, cell_width)
        
        //Now draw the border of the block
        g.setColor(Color.black)
        g.drawRect(i*cell_width, row_location*cell_width, cell_width, cell_width)
   }
   
   //Draw text
   g.setColor(Color.green)
   g.drawString("Next row: ", 0, (CollapseGame.board_height + 2)*cell_width - (1.2*cell_width).toInt)
   g.drawString("Score: " + CollapseGame.score.toString, 120, 455)
   g.drawString("Press 'R' to restart", 250, 455)
   
   //Move everything up one
   if(!CollapseGame.gameOver && CollapseGame.click)
   {
     CollapseGame.moveUp()     
   }
   else if(CollapseGame.gameOver)
   {
     g.drawString("Game Over!", 0, 455)
   }
   else
   {
     //do nothing
   }
   
   Thread.sleep(CollapseGame.speed)
   repaint
   CollapseGame.click = false
   CollapseGame.valid_removal = false
  }
}