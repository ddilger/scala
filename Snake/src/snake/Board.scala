package snake

/**
 * @author Daniel
 */
import snake._

//In cell grid, 0 denotes a free space, 1 denotes a block of food, and 2 denotes part of the snake
class Board(val cell_grid: Array[Array[Int]], val snake: Snake, val gameOver:Boolean){
  val food_cycle = 5
  def this()
  {
    this(for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
        
      } yield (if (i==scala.util.Random.nextInt(25) || j==scala.util.Random.nextInt(25))
      // if(i!=Snake.init_x || j!=Snake.init_y)
          1
        else if (i==Snake.init_x && j==Snake.init_y)
          2
        else
          0), new Snake(), false)
  }
  def eatenBlock(s:Snake) : (Int, Int) = {
    val head_x = s.head()._1
    val head_y = s.head()._2
    if(cell_grid(head_x)(head_y) == 1){
      (head_x, head_y)
    } else {
      (-1, -1)
    }
  }
  def removeEaten(eaten_block: (Int, Int), c:Array[Array[Int]]) : Array[Array[Int]] = {
    if(eaten_block == (-1, -1)){
      c
    } else {
      for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
        
      } yield (if (i == eaten_block._1 && j == eaten_block._2)
         //println("Eaten block removed")
          2
        else
          c(i)(j))
    }
  }
  def addFood(s:Snake, c: Array[Array[Int]]) : Array[Array[Int]] = {
    if(scala.util.Random.nextInt(food_cycle) == 1) {
      for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
        
      } yield (if (i==s.tail_x && j==s.tail_y)
          1
        else
          c(i)(j))
    } else {
      c
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
  def snakeCell(p: (Int,Int)):Boolean = {
   //snake.body.contains((p._1,p._2))
    cell_grid(p._1)(p._2) == 2
  }
  def repositionSnakeOnBoard(s:Snake, c:Array[Array[Int]]):Array[Array[Int]] = {
    for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
      } yield (if (i == s.head._1 && j == s.head._2)
          2
        else if (i == s.tail_x && j == s.tail_y)
          0
        else
          c(i)(j))
    }
  def growSnakeOnBoard(s:Snake, c:Array[Array[Int]]):Array[Array[Int]] = {
    for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
      } yield (
          if (i == s.body.last._1 && j == s.body.last._2)
            2
          else
            c(i)(j)
          )
  }
  def updateBoard(x_move: Int, y_move: Int) : Board = {
    val moved_snake = snake.move(x_move, y_move)
    val eaten_block = eatenBlock(moved_snake)
    val new_grid_0 = repositionSnakeOnBoard(moved_snake, cell_grid)
    val new_grid = removeEaten(eaten_block, new_grid_0)
    val new_grid_2 = addFood(moved_snake, new_grid) //simplify using function chaining
    val fed_snake = moved_snake.grow(eaten_block)
    val new_grid_3 = growSnakeOnBoard(fed_snake, new_grid_2)
    //val fed_snake = moved_snake
    if(collides(fed_snake))
    //if(false)
    {
      //println("Game Over!")
      new Board(cell_grid, snake, true)
    }
    else
    {
      new Board(new_grid_3, fed_snake, false)
    //  new Board(new_grid_2, fed_snake, false)
   // new Board(cell_grid, moved_snake)
    }
  }
  
}