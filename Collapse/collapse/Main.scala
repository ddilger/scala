package collapse

import swing._

/**
 * @author Daniel
 */
object Main  extends SimpleSwingApplication{
   type rec = java.awt.Rectangle
  def top = new MainFrame {
    title = "Collapse"
    preferredSize = new Dimension(430, 500)
    contents = new MyPanel()
  }
}