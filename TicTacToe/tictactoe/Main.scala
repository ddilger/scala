package tictactoe

import swing._

/**
 * @author Daniel
 */
object Main  extends SimpleSwingApplication{
   type rec = java.awt.Rectangle
  def top = new MainFrame {
    title = "TicTacToe"
    val height = TicTacToeGame.cell_dimension*3
    val width = height
    preferredSize = new Dimension(width+100, height+140)
    contents = new MyPanel()
  }
}