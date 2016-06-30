package logiclibrary
/**
 * @author Daniel
 */
class Formula(val clauses: Set[Clause]) {
  def this() = {
    this(Set(new Clause())) //Formula consists only of the empty set
  }
  def containsEmpty():Boolean = {
    //Can EmptyClaus be an object rather than a class?
    //EmptyClause really needs to be a type alias to Set()
    if(clauses.contains(new EmptyClaus) || clauses.isEmpty)
    {
      true
    }
    else if(clauses.filter(_.literals.size < 1).size > 0)
    {
      true
    }
    else{
      false
    }
  }
  def copy():Formula = {
    val clauses_copy = for {c <- clauses} yield c.copy
    new Formula(clauses_copy)
  }
  override def equals(that: Any): Boolean = {
    that match{
      case that: Formula =>
        val set = that.clauses.asInstanceOf[Set[Clause]]
        set.equals(this.clauses)
        //(set diff clauses).size == 0
      case _ => false
    }
  }
  override def toString():String = {
    clauses.toString
  }
}