package ailibrary

/**
 * @author Daniel
 */
object Side {
  def None = {
    0
  }
  def White = {
    1
  }
  def Black = {
    2
  }
  def opposite(s:Int):Int = {
    if(s==1)
      2
    else if(s==2)
      1
    else
      0
  }
}