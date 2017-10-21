package mathandlogic

/**
 * @author Daniel
 */
object Ackermann {
  def Ackermann_function(m: Int, n: Int) : Int =  {
    if(m == 0)
    {
      n+1;
    }
    else if(m>0 && n==0)
    {
      Ackermann_function(m-1, 1);
    }
    else if(m>0 && n>0)
    {
      Ackermann_function(m-1, Ackermann_function(m, n-1));
    }
    else
    {
      throw new IllegalArgumentException("Ackermann function parameters must be greater than or equal to zero");
    }
  }
  
}