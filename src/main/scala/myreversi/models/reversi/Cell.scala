package myreversi.models.reversi

import myreversi.models.shared.{Color, Point}

case class Cell(point: Point, reversi: Option[Reversi]) {

  def isPlacedAt(x: Int, y:Int) = point == Point(x, y)
  def isPlacedAt(p: Point) = point == p

  /**
   * CellにReversiを置く。
   * このCellにReversiが置かれていないことが前提条件。
   *
   * @param reversi Reversi
   * @return Cell
   */
  def addReversi(reversi: Reversi): Cell = {
    require(!this.hasReversi)
    copy(reversi = Some(reversi))
  }

  /**
   * Cellに置かれているReversiをひっくり返す
   * このCellにReversiが置かれていない場合はそのままのCellを返す
   *
   * @return Cell
   */
  def toggleReversi(): Cell = {
    require(this.hasReversi)

    reversi match {
      case Some(Reversi(Color.White)) => copy(reversi = Some(Reversi(Color.Black)))
      case Some(Reversi(Color.Black)) => copy(reversi = Some(Reversi(Color.White)))
      case _ => this
    }
  }

  def hasReversi: Boolean = reversi.isDefined

  def hasReversiColoredBy(color: Color): Boolean = reversi.fold(false) { _.color == color }
}
