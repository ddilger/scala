package gameoflife

/**
 * @author Daniel
 */
class Glider extends Pattern{
  val cell_grid = cells()
  override def cells(): Array[Array[Int]] = {  
    val a = Array.ofDim[Int](30,30)
    a(1)(0) = 1
    a(2)(1) = 1
    a(0)(2) = 1
    a(1)(2) = 1
    a(2)(2) = 1
    a
  }
}