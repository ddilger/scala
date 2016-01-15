package checkers

import swing._

/**
 * @author Daniel
 */
object Main  extends SimpleSwingApplication{
   type rec = java.awt.Rectangle
  def top = new MainFrame {
    title = "Checkers"
    //val height = TicTacToeGame.cell_dimension*3
    //val width = height
    preferredSize = new Dimension(500, 500)
    contents = new MyPanel()
    //I should handle buttons here, seperately from MyPanel
    //MyPanel should exist in the applet version, but the buttons should be different
    //There will be no file management in the applet version
  }
}