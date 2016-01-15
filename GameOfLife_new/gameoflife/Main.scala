package gameoflife

/**
 * @author Daniel
 */
import swing._

import gameoflife._

object Main extends SimpleSwingApplication{
  type rec = java.awt.Rectangle
  def top = new MainFrame {
    title = "Conway's Game of Life"
    preferredSize = new Dimension(400, 400)
    contents = new MyPanel()
  }
}