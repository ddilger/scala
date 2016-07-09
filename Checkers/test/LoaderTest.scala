package test

import checkers.GameLoader

/**
 * @author Daniel
 */
object LoaderTest {
  //Test connectivity with the game package
  def test0: Unit = {
    GameLoader.startGame(0)
  }
  
  //The first few test cases are hardcoded file representations
  def test1: Unit = {
   // val white_pieces = "W[(K, A, 1), (R, B, 2), (R, C, 3)]"
  }
}