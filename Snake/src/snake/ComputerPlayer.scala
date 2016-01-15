package snake

/**
 * @author Daniel
 */
class ComputerPlayer(board: Board) {
  def moveDecision(): (Int, Int) = {
    (0, 1)
  }
  def updatePlayer(new_board: Board): ComputerPlayer = {
    new ComputerPlayer(new_board)
  }
}