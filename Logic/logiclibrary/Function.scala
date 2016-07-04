package logiclibrary

/**
 * @author Daniel
 */

//Should the args be a list of Terms, to give the option of passing a function?
class Function(name:String, args:List[Literal]) extends Literal(name:String) {
  
  //Not sure how this should be defined
   override def occursIn(t:Term):Boolean = {
     false
  }
}