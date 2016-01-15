package ailibrary

/**
 * @author Daniel
 */
trait GameNode {

  def value(player: Player): Int
  def nextNodes(side: Player): List[GameNode]
}