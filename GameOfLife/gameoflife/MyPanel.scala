package gameoflife

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
  
  override def paintComponent(g: java.awt.Graphics2D) {
    super.paintComponent(g)
    g.setColor(Color.blue)
    
    for (i <- 0 to 29){
      for (j <- 0 to 29 if board.cell_grid(i)(j) == 1){
        g.fillRect(i*10, j*10, 10, 10)
      }
    }
    board = board.updateBoard()
    Thread.sleep(1000)
    repaint
  }
}