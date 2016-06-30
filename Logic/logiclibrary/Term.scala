package logiclibrary

/**
 * @author Daniel

 */


//Parent class for both Literal and Function
abstract class Term (name: String) {
  def occursIn(t:Term):Boolean
}