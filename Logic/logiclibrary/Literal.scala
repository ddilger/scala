package logiclibrary

/**
 * @author Daniel
 */
class Literal(val truth: Boolean = true, val name: String) {
  def this(name: String) = {
    this(true, name)
  }
  def copy():Literal = {
    new Literal(truth, new String(name))
  }
  def negation(): Literal = {
    new Literal(!truth, name)
  }
  override def equals(that: Any): Boolean = {
    that match{
      case that: Literal =>
      val lit = that.asInstanceOf[Literal]
      if(lit.truth == this.truth && lit.name.equals(name)){
        true
      }
      else
      {
        false
      }
      case _ => false
    }
  }
  override def toString():String = {
    if(truth){
      name
    }
    else{
      "~" + name
    }
  }
}