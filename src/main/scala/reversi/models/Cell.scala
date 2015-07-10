package reversi.models

case class Cell(x: Int, y:Int, reversi: Option[Reversi]) {

  def addReversi(reversi: Reversi): Cell = copy(reversi = Some(reversi))

  def removeReversi: Cell = copy(reversi = None)

  def hasReversi: Boolean = reversi.isDefined

}
