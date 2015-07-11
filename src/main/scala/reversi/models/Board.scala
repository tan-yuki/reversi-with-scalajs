package reversi.models

case class Board(edge: Int, cellCollection: CellCollection) {
  require(edge >= 4 && edge % 2 == 0)
  require(cellCollection.length == edge * edge)

  def addReversi(x: Int, y: Int, reversi: Reversi): Board = {
    val newCells = cellCollection.map {
      case c if c.x == x && c.y == y => Cell(x, y, Some(reversi))
      case c => c
    }

    copy(cellCollection = newCells)
  }
}

object Board {
  private def initialReversiMapping(edge: Int): Map[(Int,Int), Reversi] = {
    val middle:Int = edge / 2

    Map(
      (middle,     middle)     -> Reversi(Color.Black),
      (middle + 1, middle)     -> Reversi(Color.White),
      (middle,     middle + 1) -> Reversi(Color.White),
      (middle + 1, middle + 1) -> Reversi(Color.Black)
    )
  }

  def createEmptyBoard(edge: Int): Board = {
    val cells: Seq[Cell] = (for {
      x <- (1 to edge).toList
      y <- (1 to edge).toList
    } yield {
      Cell(x, y, None)
    }).toSeq

    Board(edge, cells)
  }

  def initialize(edge: Int): Board = {
    val emptyBoard: Board = createEmptyBoard(edge)

    initialReversiMapping(edge).foldLeft(emptyBoard) {
      case (board, ((x, y), reversi)) => board.addReversi(x, y, reversi)
    }
  }
}
