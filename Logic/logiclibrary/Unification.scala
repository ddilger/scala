package logiclibrary

/**
 * @author Daniel
 */

//page 84 in Logic for Computer Scientists
//Am I using Robinson's or the Martelli-Montanari unification algorithm? (Robinson's)
//Do I need a function to compute the disagreement set? (yes)
object Unification {
  
  //disagreement
  
  //produce substitution tuples
  //In the tuple returned, the first clause is replaced by the second.
  //e.g [x/y], x is replaced by y
  def apply_substitution(v: Variable, l: Literal):Substitution = {
    new Substitution(v, l)
  }
  
  //Definition of "Lsub", as described in the unification algorithm
  def substitute(L: List[Literal], sub: List[Substitution]): List[Literal] = {
    null
  }

  //I will need a way to produce a unification nondeterministically
  //Maybe I can have another version of this function which takes a list of previously
  //produced unifiers, and then produces a unifier outside of this
  //This particular function, however, is deterministic
  def unify(L: List[Literal]): Unifier = {
    var sub: List[Substitution] = List()
    var Lsub = substitute(L, sub)
    while(Lsub.length > 1){
      /* Scan the literals in Lsub from left to right, 
       * until the first position is found where in at
       * least two literals (say, L1 and L2) the
       * corresponding symbols are different */
      
      //None of these symbols is a variable
      if(Lsub.filter((t:Literal) => t.isInstanceOf[Variable]).length == 0){
        return NonUnifiable 
      }
      else{
       /* Let x be the variable, and let t be a term that
        * is different from x and which starts at this
        * position in another literal (this can also be a variable)
        * */ 
        val x = new Variable("blah")
        val t = new Literal("blah")
        if(x.occursIn(t)){
          return NonUnifiable
        }
        else{
          val s = apply_substitution(x, t)
          sub = s :: sub
          
        }
      }
    }
    new Unifier(sub)
  }
}