package reversi.models

case class Cell(x: Int, y:Int, reversi: Option[Reversi]) {
  require(x >= 1)
  require(y >= 1)

  def addReversi(reversi: Reversi): Cell = copy(reversi = Some(reversi))

  def removeReversi(): Cell = copy(reversi = None)

  def hasReversi: Boolean = reversi.isDefined

  def hasReversiColoredBy(color: Color): Boolean = reversi.fold(false) { _.color == color }
}
