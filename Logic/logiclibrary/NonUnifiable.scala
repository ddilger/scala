package logiclibrary

/**
 * @author Daniel

 */

//A special case of a unification list. Simply represents a failure to unify
//a better definition of "non-unifiable" than simply returning a null in the unification function
object NonUnifiable extends Unifier(null) {
  
}