package checkers

import ailibrary._

/**
 * @author Daniel
 */
class King(override val side: Int, x: Int,  y: Int) extends CheckersPiece(side: Int, x: Int, y: Int){
  override def kinged():Boolean = {
    true //once a king, always a king
  }
  
    //The biggest problem with king moves is avoiding cycles. It will need to keep an updated board
  //with it as it generates moves
  //So, instead of a
   override def moves(b: Board):List[Move] = {
      var move_list = super.moves(b)  //I shouldn't have this. I should overrwrite altogether
      
      //consider y_direction to be the opposite of what it was in super.moves
      val y_direction = (if (side == Side.White) 1 else -1)
      
      if (b.inBounds(x-1, y+y_direction) && b.cell_grid(x-1)(y+y_direction).isEmpty)
        move_list = new Move(side, (x,y),(x-1, y+y_direction))::move_list
      if (b.inBounds(x+1, y+y_direction) && b.cell_grid(x+1)(y+y_direction).isEmpty)
        move_list = new Move(side, (x,y),(x+1, y+y_direction))::move_list
        
        move_list
    }
   
  override def toString():String = {
    val str = "King " + side + " " + x + " " + y + "\n"
    str
  }
  
}