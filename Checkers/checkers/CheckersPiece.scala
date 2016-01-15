package checkers

import ailibrary._

/**
 * @author Daniel
 */
class CheckersPiece(override val side: Int, x: Int, y: Int) extends Piece(side:Int) {
  def this() = {
    this(0,0,0) //denotes an empty square (I might want to use "Maybe" later...)
  }
  //should I add a static method named "none" for producing empty pieces? 
  def empty():Boolean = {
    if(side == 0){
      true
    }
    else
      false
  }
  def isEmpty():Boolean = {
    empty
  }
  def adjacent(i:Int, j:Int):Boolean = {
    val y_direction = (if (side == Side.White) -1 else 1)
    (i == x-1 && j==y+y_direction) || (i == x+1 && j==y+y_direction)
  }
  def adjacent(c:(Int,Int)):Boolean = {
    adjacent(c._1, c._2)
  }
  def jumpsFrom(b: Board, i: Int, j:Int):List[Move] = {
    //First calculate where simple forward movements can be performed
    val y_direction = (if (side == Side.White) -1 else 1)
    //Check for jumps to left and right
    var jumps = List[Move]()

    if(b.inBounds(i-2, j+2*y_direction) && b.pieceAt(i-2, j+2*y_direction).side == 0){
      if(b.pieceAt(i-1, j+y_direction).side == Side.opposite(side))
        jumps = new Move(side, (i,j),(i-2, j+2*y_direction), List((i-1, j+y_direction)))::jumps
    }
    if(b.inBounds(i+2, j+2*y_direction) && b.pieceAt(i+2, j+2*y_direction).side == 0){
      if(b.pieceAt(i+1, j+y_direction).side == Side.opposite(side))
        jumps = new Move(side, (i,j),(i+2, j+2*y_direction), List((i+1, j+y_direction)))::jumps
    }
    //for each element in jumps, produce another "jumpsFrom" list and prepend it to this list
    
    //return the list of jumps
    jumps
  }
  

  def moves(b: Board):List[Move] = {
    if (empty){
      List()
    }
    else{
      var move_list = List[Move]()
      
      //First calculate where simple forward movements can be performed
      val y_direction = (if (side == Side.White) -1 else 1)
      if (b.inBounds(x-1, y+y_direction) && b.cell_grid(x-1)(y+y_direction).isEmpty)
        move_list = new Move(side, (x,y),(x-1, y+y_direction))::move_list
      if (b.inBounds(x+1, y+y_direction) && b.cell_grid(x+1)(y+y_direction).isEmpty)
        move_list = new Move(side, (x,y),(x+1, y+y_direction))::move_list
        
    
      //Next, include jumps in the list of moves
      val jumps = jumpsFrom(b, x, y)
      move_list = move_list++jumps
      
      //Finally, return the list of all moves collected
      move_list
    }
  }
  
  def kinged():Boolean = {
    if(side == Side.White){
      y == 0
    }
    else if(side == Side.Black){
      y == 7
    }
    else{
      false
    }
  }

  def movedTo(new_x: Int, new_y: Int): CheckersPiece = {
    if(kinged){
      new King(side, new_x, new_y)
    }
    else{
      new CheckersPiece(side, new_x, new_y)
    }
  }
  
  //produces a deep copy of this piece
  def copy():CheckersPiece = {
    new CheckersPiece(side, x, y)
  }
  
  override def toString():String = {
    val str = "Regular " + side + " " + x + " " + y + "\n"
    str
  }
}