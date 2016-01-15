package chess

/**
 * @author Daniel
 */
object DefaultBoard {
  val setup = piece_setup()
  def piece_setup(): Array[Array[Piece]] = {
    val a = Array.ofDim[Piece](8,8)
    //Black Pieces
    a(0)(0) = new Rook(Side.Black)
    a(0)(1) = new Knight(Side.Black)
    a(0)(2) = new Bishop(Side.Black)
    a(0)(3) = new Queen(Side.Black)
    a(0)(4) = new King(Side.Black)
    a(0)(5) = new Bishop(Side.Black)
    a(0)(6) = new Knight(Side.Black)
    a(0)(7) = new Rook(Side.Black)
    
    a(1)(0) = new Pawn(Side.Black)
    a(1)(1) = new Pawn(Side.Black)
    a(1)(2) = new Pawn(Side.Black)
    a(1)(3) = new Pawn(Side.Black)
    a(1)(4) = new Pawn(Side.Black)
    a(1)(5) = new Pawn(Side.Black)
    a(1)(6) = new Pawn(Side.Black)
    a(1)(7) = new Pawn(Side.Black)
    
    
    //White Pieces
    a(7)(0) = new Rook(Side.White)
    a(7)(1) = new Knight(Side.White)
    a(7)(2) = new Bishop(Side.White)
    a(7)(3) = new Queen(Side.White)
    a(7)(4) = new King(Side.White)
    a(7)(5) = new Bishop(Side.White)
    a(7)(6) = new Knight(Side.White)
    a(7)(7) = new Rook(Side.White)
    
    a(6)(0) = new Pawn(Side.White)
    a(6)(1) = new Pawn(Side.White)
    a(6)(2) = new Pawn(Side.White)
    a(6)(3) = new Pawn(Side.White)
    a(6)(4) = new Pawn(Side.White)
    a(6)(5) = new Pawn(Side.White)
    a(6)(6) = new Pawn(Side.White)
    a(6)(7) = new Pawn(Side.White)
    
    //return the array of pieces
    a
  }
}