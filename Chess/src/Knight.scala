package chess

/**
 * @author Daniel
 */
class Knight(override val side: Side.Value, override val row: Int, override val col: Int) 
extends Piece(side, row, col){
  val piece_type = PieceType.Knight
  def moves(board: Board): List[Move] = {
    null
  }
}