package chess

/**
 * @author Daniel
 */
class Board (val pieces: Array[Array[Piece]]){
  def this(){
    this(DefaultBoard.setup)
  } 
}