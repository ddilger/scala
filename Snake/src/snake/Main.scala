package snake

import swing._

/**
 * @author Daniel
 */
object Main  extends SimpleSwingApplication{
   type rec = java.awt.Rectangle
  def top = new MainFrame {
    title = "Snake"
    preferredSize = new Dimension(400, 400)
    contents = new MyPanel()
  }
}