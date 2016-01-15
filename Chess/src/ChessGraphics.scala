package chess

/**
 * @author Daniel
 */
 import javax.swing.ImageIcon;
 import java.awt.Image;
object ChessGraphics {
  var focus = false
  var focus_row = 0
  var focus_col = 0
  val focus_img = new ImageIcon("src/resources/Focus.png").getImage()
  
  def piecePresent(row: Int, col: Int): Boolean = {
    false
  }
  
  def moveIllustrations(row: Int, col: Int): List[(Int, Int, Image)] = {
    null
  }
  
  def pieceImages(board: Board): List[(Int, Int, Image)] = {
    def getPieceImage(piece: PieceType.Value, side: Side.Value): Image = {
      (piece, side) match {
        case (PieceType.King, Side.White) 
          => new ImageIcon("src/resources/WhiteKing.png").getImage()
        case (PieceType.King, Side.Black)
          => new ImageIcon("src/resources/BlackKing.png").getImage()
        
        case (PieceType.Queen, Side.White)
          => new ImageIcon("src/resources/WhiteQueen.png").getImage()
        case (PieceType.Queen, Side.Black)
          => new ImageIcon("src/resources/BlackQueen.png").getImage()
          
        case (PieceType.Rook, Side.White)
          => new ImageIcon("src/resources/WhiteRook.png").getImage()
        case (PieceType.Rook, Side.Black)
          => new ImageIcon("src/resources/BlackRook.png").getImage()
          
        case (PieceType.Knight, Side.White)
          => new ImageIcon("src/resources/WhiteKnight.png").getImage()
        case (PieceType.Knight, Side.Black)
          => new ImageIcon("src/resources/BlackKnight.png").getImage()
          
        case (PieceType.Bishop, Side.White)
          => new ImageIcon("src/resources/WhiteBishop.png").getImage()
        case (PieceType.Bishop, Side.Black)
          => new ImageIcon("src/resources/BlackBishop.png").getImage()
          
        case (PieceType.Pawn, Side.White)
          => new ImageIcon("src/resources/WhitePawn.png").getImage()
        case (PieceType.Pawn, Side.Black)
          => new ImageIcon("src/resources/BlackPawn.png").getImage()
      }
    }
   //flattens the 2d representation of the board to a list
   val tuples = for {
      row <- Array.range(0,  8)
    } yield for {
      col <- Array.range(0,  8)
      if board.pieces(row)(col) != null }  yield (row, col, getPieceImage(board.pieces(row)(col).piece_type, board.pieces(row)(col).side))
   val tup_array = tuples.flatten
   tup_array.toList
  }
}