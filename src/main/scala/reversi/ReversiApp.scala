package reversi

import reversi.models.{CellCollection, Board}

import reversi.views.BoardView


import scala.scalajs.js
import org.scalajs.jquery.{JQuery, jQuery}

object ReversiApp extends js.JSApp {

  val Edge = 8

  def main(): Unit = refresh(Board.initialize(Edge))

  def refresh(board: Board): Unit =
    jQuery("#main").empty().append(BoardView(board).render())

  def refresh(cellCollection: CellCollection): Unit =
    refresh(Board(Edge, cellCollection))

}
