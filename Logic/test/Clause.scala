package test

/**
 * @author Daniel
 */
//Probably want case classes or something
class EmptyClaus(override val literals: Set[Literal]) extends Clause(literals: Set[Literal]) {
  def this() = {
    this(Set())
  }
}

class Clause(val literals: Set[Literal]) {
  def this() = {
    this(Set())
  }
  def copy():Clause = {
    val new_literals = for{l <- literals} yield l.copy
    new Clause(new_literals)
  }
  def isEmpty():Boolean = {
    if(literals.isEmpty){
      true
    }
    else{
      false
    }
  }
  def contains(L: Literal) = {
    if(literals.contains(L)){
      true
    }
    else{
      false
    }
  }
   override def equals(that: Any): Boolean = {
    that match{
      case that: Clause =>
        val set = that.literals.asInstanceOf[Set[Literal]]
        set.equals(this.literals)
      case _ => false
    }
  }
  override def toString():String = {
    literals.toString
  }
}

