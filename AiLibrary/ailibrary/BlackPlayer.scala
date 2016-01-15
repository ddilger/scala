package ailibrary

/**
 * @author Daniel
 */
object BlackPlayer extends Player{
  def side():Int = {
    2
  }
  def opponent():Player = {
    WhitePlayer
  }
}