package snake

/**
 * @author Daniel
 */
import snake._

class Board(val cell_grid: Array[Array[Int]], val snake: Snake, val gameOver:Boolean){
  val food_cycle = 20
  def this()
  {
    this(for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
        
      } yield (if (i==scala.util.Random.nextInt(25) || j==scala.util.Random.nextInt(25))
          1
        else
          0), new Snake(), false)
  }
  def eatenBlock() : (Int, Int) = {
    val head_x = snake.head()._1
    val head_y = snake.head()._2
    if(cell_grid(head_x)(head_y) == 1){
      (head_x, head_y)
    } else {
      (-1, -1)
    }
  }
  def removeEaten(eaten_block: (Int, Int)) : Array[Array[Int]] = {
    if(eaten_block == (-1, -1)){
      cell_grid
    } else {
      for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
        
      } yield (if (i == eaten_block._1 && j == eaten_block._2)
         //println("Eaten block removed")
          0
        else
          cell_grid(i)(j))
    }
  }
  def addFood(grid: Array[Array[Int]]) : Array[Array[Int]] = {
    if(scala.util.Random.nextInt(food_cycle) == 1) {
      for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
        
      } yield (if (i==snake.tail_x && j==snake.tail_y)
          1
        else
          grid(i)(j))
    } else {
      grid
    }
  }
  def outOfBounds(x: Int, y: Int) = {
    if (x < 0){
      true
    } else if (x > 29) {
      true
    } else if (y < 0) {
      true
    } else if (y > 29) {
      true
    } else {
      false
    }
  }
  def collides(s: Snake) = {
    s.body.distinct.size != s.body.size
  }
  def snakeOutOfBounds(s: Snake) : Boolean =  {
    val head_x = s.head()._1
    val head_y = s.head()._2
    outOfBounds(head_x, head_y)
  }
  def updateBoard(x_move: Int, y_move: Int) : Board = {
    val moved_snake = snake.move(x_move, y_move)
    val eaten_block = eatenBlock()
    val new_grid = removeEaten(eaten_block)
    val new_grid_2 = addFood(new_grid) //simplify using function chaining
    val fed_snake = moved_snake.grow(eaten_block)
    //val fed_snake = moved_snake
    if(collides(fed_snake))
    //if(false)
    {
      //println("Game Over!")
      new Board(cell_grid, snake, true)
    }
    else
    {
      new Board(new_grid_2, fed_snake, false)
   // new Board(cell_grid, moved_snake)
    }
  }
  
}