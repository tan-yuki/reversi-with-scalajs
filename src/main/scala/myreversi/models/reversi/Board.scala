package myreversi.models.reversi

import myreversi.models.player.{CurrentPlayerState, PlayerPare, Player}
import myreversi.models.shared.{Color, Point}

case class Board(edge: Int,
                 cellCollection: CellCollection,
                 currentPlayerState: CurrentPlayerState) {

  require(edge >= 4 && edge % 2 == 0)
  require(cellCollection.length == edge * edge)

  private def addReversi(p: Point, reversi: Reversi): Board = {
    val newCells = cellCollection.map {
      case c if c.isPlacedAt(p) => Cell(p, Some(reversi))
      case c => c
    }

    copy(cellCollection = newCells)
  }
}

object Board {
  private def initialReversiMapping(edge: Int): Map[Point, Reversi] = {
    val middle:Int = edge / 2

    Map(
      Point(middle,     middle)     -> Reversi(Color.Black),
      Point(middle + 1, middle)     -> Reversi(Color.White),
      Point(middle,     middle + 1) -> Reversi(Color.White),
      Point(middle + 1, middle + 1) -> Reversi(Color.Black)
    )
  }

  /**
   * Reversiを何も置いていない状態のBoardを生成する
   *
   * @param edge 一辺の長さ
   * @param currentPlayerState Current player state
   * @return
   */
  def createEmptyBoard(edge: Int, currentPlayerState: CurrentPlayerState): Board = {
    val cells: Seq[Cell] = (for {
      x <- (1 to edge).toList
      y <- (1 to edge).toList
    } yield {
      Cell(Point.at(x, y), None)
    }).toSeq

    Board(edge, cells, currentPlayerState)
  }

  /**
   * 初期のReversiを置いた状態のBoardを生成する
   *
   * @param edge 一辺の長さ
   * @param currentPlayerState Current player state
   * @return
   */
  def initialize(edge: Int, currentPlayerState: CurrentPlayerState): Board = {
    val emptyBoard: Board = createEmptyBoard(edge, currentPlayerState)

    initialReversiMapping(edge).foldLeft(emptyBoard) {
      case (board, (p, reversi)) => board.addReversi(p, reversi)
    }
  }
}
