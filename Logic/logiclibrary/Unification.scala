package logiclibrary

/**
 * @author Daniel
 */

//page 84 in Logic for Computer Scientists
object Unification {
  
  
  //Definition of "Lsub", as described in the unification algorithm
  def substitute(L: List[Literal], sub: List[(Variable, Literal)]): List[Literal] = {
    null
  }

  def unify(L: List[Literal]): List[(Variable, Literal)] = {
    var sub: List[(Variable, Literal)] = List()
    var Lsub = substitute(L, sub)
    while(Lsub.length > 1){
      /* Scan the literals in Lsub from left to right, 
       * until the first position is found where in at
       * least two literals (say, L1 and L2) the
       * corresponding symbols are different */
      
      //None of these symbols is a variable
      if(Lsub.filter((t:Literal) => t.isInstanceOf[Variable]).length == 0){
        return null //Need a better definition of "non-unifiable"
      }
      else{
       /* Let x be the variable, and let t be a term that
        * is different from x and which starts at this
        * position in another literal (this can also be a variable)
        * */ 
        
      }
    }
    sub
  }
}