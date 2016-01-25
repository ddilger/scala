package snake

/**
 * @author Daniel
 */

//companion object for Snake
object Snake{
  val init_x = 1
  val init_y = 1
}

//The "tail" variable marks where a tail will be made
class Snake(val body: List[(Int, Int)], x_dir: Int, y_dir: Int, val tail_x: Int, val tail_y: Int){
 //import Snake._ why can't I import the companion object?
  def this()
  {
    this(List((Snake.init_x, Snake.init_y)), 30, 0, 0, 0)
  }
  def head(): (Int, Int) = {
    body(0)
  }
  def neck(): (Int, Int) = {
    if (body.length == 1)
    {
      head()
    }
    else
    {
      body(1)
    }
  }
  //This function needs to be removed during refactoring
  def goingBackwards(): Boolean = {
    false
  }
  def move(x_move: Int, y_move: Int): Snake = {
    //println("Move being made")
    val backwards_body = for { i <- List.range(0, body.length).reverse } yield 
        (if(i == 0) {
          ((body(0)._1 + x_move + 30)%30, (body(0)._2 + y_move + 30)%30)
        } else {
          body(i-1)
        }
          )
    new Snake(backwards_body.reverse, x_move, y_move, body.last._1, body.last._2)
    
  }
  //It needs to grow into the space of the last location of the tail
  def grow(food: (Int, Int)): Snake = {
    if(food == (-1, -1)){
      this
    } else {
      val new_tail = ((tail_x + 30)%30, (tail_y + 30)%30)
    //  val new_x_dir = if(x_dir == 1){0} else {1}
    //  val new_y_dir = if(y_dir == 1){0} else {1}
    //  new Snake(body :+ new_tail, new_x_dir, new_y_dir)
      new Snake(body :+ new_tail, x_dir, y_dir, body.last._1, body.last._2) 
    }
  }
}