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
    clauses.contains(new EmptyClaus) || clauses.isEmpty
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
      case _ => false
    }
  }
  override def toString():String = {
    clauses.toString
  }
}