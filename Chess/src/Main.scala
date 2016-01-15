package chess

/**
 * @author Daniel
 */
import swing._

import chess._

object Main extends SimpleSwingApplication{
  type rec = java.awt.Rectangle
  def top = new MainFrame {
    title = "Normal Chess"
    preferredSize = new Dimension(617, 639)
    contents = new ChessPanel()
    println(System.getProperty("user.dir"))
  }
}