package ailibrary

/**
 * @author Daniel
 */
trait Player {
  def side():Int //can be overriden to return an enum, probably
  def opponent():Player
}