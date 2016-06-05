package chess

/**
 * @author Daniel
 */
 import scala.swing._
 import scala.swing.event._
 import java.awt.Color
 import java.awt.Image
 import java.awt.image.BufferedImage
 import javax.imageio.ImageIO
 import java.io.File
 import java.awt.Graphics2D
 import java.awt.Point
 import javax.swing.ImageIcon
 
 import chess._
 
class ChessPanel extends Panel{
  opaque = true
  background = Color.black
  val board_width = 600
  val square_width = board_width/8
  def rowClicked(p: Point): Int = {
    math.floor(p.getY()/square_width) toInt
  }
  def colClicked(p: Point):Int = {
    math.floor(p.getX()/square_width) toInt
  }
  override def paintComponent(g: java.awt.Graphics2D) {
   
    listenTo(mouse.clicks)
   
    reactions += {
      case e: MouseClicked =>
        ChessGraphics.focus = true
        ChessGraphics.focus_row = rowClicked(e.point) 
        ChessGraphics.focus_col = colClicked(e.point)
    }
    super.paintComponent(g)
    
    val board_img = new ImageIcon("src/resources/NormalBoard.png").getImage()
    
    g.drawImage(board_img, 0, 0, board_width, board_width, null)
    
    val board = new Board()
    if (ChessGraphics.focus)
      g.drawImage(ChessGraphics.focus_img, square_width*ChessGraphics.focus_col, square_width*ChessGraphics.focus_row, square_width, square_width, null)
    val piece_images = ChessGraphics.pieceImages(board)
    for {
      img <- piece_images
    } g.drawImage(img._3, square_width*img._2, square_width*img._1, square_width, square_width, null)
    repaint()
  }
}