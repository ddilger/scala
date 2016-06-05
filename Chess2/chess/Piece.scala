package chess

/**
 * @author Daniel
 */


abstract class Piece(val side: Side.Value, val row: Int, val col: Int){
  val piece_type: PieceType.Value
  def moves(board: Board): List[Move]
}