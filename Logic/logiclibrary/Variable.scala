package logiclibrary

/**
 * @author Daniel
 */

//Literl and variable should both inherit from abstract class "Term"
class Variable (override val name: String) 
  extends Literal(name: String) {
    
}