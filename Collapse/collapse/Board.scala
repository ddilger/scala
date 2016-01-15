package collapse

/**
 * @author Daniel
 */
class Board(val cell_grid: Array[Array[Int]]) {
  def this()
  {
    this(for {
      i <- Array.range(0,  CollapseGame.board_width)
    } yield for {
      j <- Array.range(0,  CollapseGame.board_height)
        
      } yield (if (j >= CollapseGame.board_height - CollapseGame.start_height)
      scala.util.Random.nextInt(CollapseGame.num_colors) + 1 
      else 0)
      )
  }
  //This function produces a row which guarantees "min_removal" contiguous blocks
  /*
  def rowWithMinRemoval(): Array[Int]{
    
  }
  * */
  
  def moveUp() : Board = {
    val new_grid = for {
      i <- Array.range(0,  CollapseGame.board_width)
    } yield for {
      j <- Array.range(0,  CollapseGame.board_height)
        
      } yield (if (j < CollapseGame.board_height - 1)
      cell_grid(i)(j+1) 
      else CollapseGame.next_row(i))
      new Board(new_grid)
  }
  
  //Not sure whether "replace color" should work with a var or a val
  //For a val, there surely is a more elegant way to do this...
  def replaceColor(cells : Array[Array[Int]], node: (Int, Int)): Array[Array[Int]] = {
    for {
      i <- Array.range(0,  CollapseGame.board_width)
    } yield for {
      j <- Array.range(0,  CollapseGame.board_height)
        
      } yield (if ((i,j) == node)
      0 //Color will always be "replaced" to black in floodfill
      else cells(i)(j))
      
  }
  
  //floodfill is a helper method to selectedCells
  /*
  def floodFill(cells : Array[Array[Int]], node: (Int, Int), target_color: Int): Int = {
    if(cells(node._1)(node._2) != target_color) {
      cells
    }
    else
    {
      cells
    }
  }
  
  //Applies floodfill algorithm to find cells contiguous to the one clicked
  //In array, 0 denotes untouched, 1 denotes touched cell
  def cellsRemoved(x: Int, y: Int): Array[Array[Int]] = {
    val target_color = cell_grid(x)(y)
    for {
      i <- Array.range(0,  CollapseGame.board_width)
    } yield for {
      j <- Array.range(0,  CollapseGame.board_height)
        
      } yield floodFill(cell_grid, (i,j), target_color)
  }
  * */
  //Flood fill is O(n^2), so I don't need to worry about this algorithm's performance
  def toRemove(target_color: Int, start_x: Int, start_y: Int, grid: Array[Array[Int]]): List[(Int, Int)] = {
    var removed_cells: List[(Int,Int)] = List()
    
    if(target_color == 0)
    {
      //do nothing
    }
    //Maybe I want to do a wrap-around in the future (like in snake)?
    else if(start_x < 0 || start_y < 0)
    {
      //do nothing
    }
    else if(start_x >= CollapseGame.board_width || start_y >= CollapseGame.board_height)
    {
      //do nothing
    }
    else if(grid(start_x)(start_y) != target_color)
    {
      //simply return an empty list (nothing needs to be done here)
    }
    else
    {
      grid(start_x)(start_y) = 0
      removed_cells ::= (start_x, start_y)
      removed_cells = removed_cells ++ toRemove(target_color, start_x - 1, start_y, grid)
      removed_cells = removed_cells ++ toRemove(target_color, start_x + 1, start_y, grid)
      removed_cells = removed_cells ++ toRemove(target_color, start_x, start_y - 1, grid)
      removed_cells = removed_cells ++ toRemove(target_color, start_x, start_y + 1, grid)
      
    }
    
   //println("Removed cells: " + removed_cells.size)
   removed_cells
  }
  //There's surely a built-in way to perform a deep copy in Scala
  def arrayCopy(original: Array[Array[Int]]) = {
    for {
      i <- Array.range(0,  CollapseGame.board_width)
    } yield for {
      j <- Array.range(0,  CollapseGame.board_height)
      } yield original(i)(j)
  }
  def numPoints(num_removed: Int) = {
    num_removed*num_removed
  }
  def cellsRemoved(x: Int, y: Int): Array[Array[Int]] = {
    val target_color = cell_grid(x)(y)
    val removed_cells = toRemove(target_color, x, y, arrayCopy(cell_grid))
    val num_removed = removed_cells.size
    if(num_removed >= CollapseGame.min_removal)
    {
      CollapseGame.valid_removal = true
      CollapseGame.score += numPoints(num_removed)
    }
    //println(num_removed)
    for {
      i <- Array.range(0,  CollapseGame.board_width)
    } yield for {
      j <- Array.range(0,  CollapseGame.board_height)
        
      } yield (if (num_removed >= CollapseGame.min_removal && removed_cells.contains(i,j))
        0
      else cell_grid(i)(j)
        )
  }
  
  //removing cells will leave some cells "floating". Have them go down until they
  //are above another cell (resting on top of it)

  def fallIntoPlace(floating_board: Array[Array[Int]]) : Array[Array[Int]] = {
    for {
      col <- floating_board
    } yield {
      val no_gaps = col.filter(_ != 0)
      //val missing_rows = CollapseGame.board_height - no_gaps.size
      val missing_rows = floating_board.size - no_gaps.size
      val gaps = for {i <- Array.range(0, missing_rows)} yield {0}
      gaps ++ no_gaps 
      }
  }
  def slideLeft(floating_board: Array[Array[Int]]) : Array[Array[Int]] = {
    for {
      col <- floating_board
    } yield {
      val no_gaps = col.filter(_ != 0)
      //val missing_rows = CollapseGame.board_height - no_gaps.size
      val missing_rows = floating_board.size - no_gaps.size
      val gaps = for {i <- Array.range(0, missing_rows)} yield {0}
      gaps ++ no_gaps 
      }
  }
  //helper function for removeGaps
  def listGaps(row: Array[Int]): Array[Int] = {
    val indices = for(cell_loc <- Array.range(0, CollapseGame.board_width);
      if (row(cell_loc) == 0)) yield {cell_loc}
    indices
  }
  //helper function for slideLeft
  def blankRow():Array[Int] = {
    for{i<-Array.range(0, CollapseGame.board_width-1)} yield {0}
  }
  
  //helper function for removeGaps
  def slideLeft(gap_board: Array[Array[Int]], gaps: Array[Int]): Array[Array[Int]] = {
    val num_gaps = gaps.length
    //val first_row = CollapseGame.board_height - 1
    //val first_gap = gaps(0)
    val gapless_rows = gap_board.filter(_.contains(0))
    val gap_rows = for{i<-Array.range(0, num_gaps)} yield {blankRow()}
    gapless_rows ++ gap_rows
  }
  
  //slides one side of the board against the other if a column contains no rows
  //Unfortunately it requires the grid to be square shaped
  //I need to figure out why being square-shaped matters...
  def removeGaps(gap_board: Array[Array[Int]]): Array[Array[Int]] = {
    //Simply determine where the first gap (from the right) is, and then slide everything
    //by n squares, where n is the number of gaps in the bottom row
    val t = gap_board.transpose
    val gaps = listGaps(t.last)
    val num_gaps = gaps.length
   
    //if(gap_board(CollapseGame.board_height - 1).contains(0))
    //println(num_gaps)
    if(num_gaps > 0)
    {
      /*
      val b = gap_board.transpose
      val b2 = slideLeft(b)
      b2.transpose
      */

      val b = gap_board.transpose
      //val b2 = slideLeft(b, gaps)
      val b2 = slideLeft(b)
      b2.transpose
    }
    else
    {
      gap_board //there were no gaps in this board after all
    }
  }
}