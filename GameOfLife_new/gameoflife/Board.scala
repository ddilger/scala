package gameoflife

/**
 * @author Daniel
 */
import gameoflife._

//class Board(startP: Pattern) {
  class Board(val cell_grid: Array[Array[Int]]){
  def this(){
    this(new Glider().cell_grid)
  }
  
  //Counts the number of neighbors for the cell at (x,y)
  def neighbors(x: Int, y: Int): Int = {
    //Check if a neighbor exists at (a,b)
    def exists(a: Int, b: Int): Int = {
      if(a < 0 || a > 29)
        0
      else if (b < 0 || b > 29)
        0
      else if (cell_grid(a)(b) == 1)
        1
      else
        0
    }
    exists(x-1, y-1) + 
    exists(x-1, y) + 
    exists(x-1, y+1) + 
    exists(x, y-1) + 
    exists (x, y+1) +
    exists (x+1, y-1) + 
    exists (x+1, y) + 
    exists (x+1, y+1)
  }
  def updateBoard(): Board = {
    val nextBoard = for {
      i <- Array.range(0,  30)
    } yield for {
      j <- Array.range(0,  30)
        
      } yield (if (this.cell_grid(i) (j) == 1 && neighbors(i, j) == 2)
          1
        else if (neighbors(i, j) == 3)
          1
        else
          0)
      //new Board()
        new Board(nextBoard)
  }
  
}