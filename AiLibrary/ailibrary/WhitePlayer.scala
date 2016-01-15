package ailibrary

/**
 * @author Daniel
 */
object WhitePlayer extends Player{
  def side():Int = {
    1
  }
  def opponent():Player = {
    BlackPlayer
  }
}