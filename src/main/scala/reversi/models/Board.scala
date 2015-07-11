package reversi.models

case class Board(edge: Int,
                 cellCollection: CellCollection,
                 player: Player) {

  require(edge >= 4 && edge % 2 == 0)
  require(cellCollection.length == edge * edge)

  def addReversi(x: Int, y: Int, reversi: Reversi): Board = {
    val newCells = cellCollection.map {
      case c if c.point.x == x && c.point.y == y => Cell(Point(x, y), Some(reversi))
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

  /**
   * Reversiを何も置いていない状態のBoardを生成する
   *
   * @param edge 一辺の長さ
   * @param currentPlayer Current player
   * @return
   */
  def createEmptyBoard(edge: Int, currentPlayer: Player): Board = {
    val cells: Seq[Cell] = (for {
      x <- (1 to edge).toList
      y <- (1 to edge).toList
    } yield {
      Cell(Point.at(x, y), None)
    }).toSeq

    Board(edge, cells, currentPlayer)
  }

  /**
   * 初期のReversiを置いた状態のBoardを生成する
   *
   * @param edge 一辺の長さ
   * @param currentPlayer Current player
   * @return
   */
  def initialize(edge: Int, currentPlayer: Player): Board = {
    val emptyBoard: Board = createEmptyBoard(edge, currentPlayer)

    initialReversiMapping(edge).foldLeft(emptyBoard) {
      case (board, ((x, y), reversi)) => board.addReversi(x, y, reversi)
    }
  }
}
