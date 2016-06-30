package logiclibrary

/**
 * @author Daniel
 */

//Should the args be a list of Terms, to give the option of passing a function?
class Function(name:String, args:List[Literal]) extends Term(name:String) {
  
  //Not sure how this should be defined
   def occursIn(t:Term):Boolean = {
     false
  }
}