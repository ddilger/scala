package test

import mathandlogic._

/**
 * @author Daniel
 */
object Main {

  //Note: The JVM does not optimize recursion well enough to allow for large input values
  
  //Simplest test
  def simplest_test(): Unit = {
    for(m <- 0 to 3){
      for(n <- 0 to 3){
        val test_result = Ackermann.Ackermann_function(m,n)
        println("Ackermann(" + m + ", " + n + ") is " + test_result)
      }
    }    
  }
  
  //Tests that involve making a closure out of the second parameter of Ackermann()
  
  //Tests that involve making a closure out of the first parameter of Ackermann()
  
  //Tests that involve producing partially recursive functions out of Ackermann (a totally recursive function)
  //See "Logic for Computer Science" by Jean Gallier for more
  
  
  //Main function for calling the tests
  def main(args: Array[String]): Unit = {
    simplest_test()
  }

}