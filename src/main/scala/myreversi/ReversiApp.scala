package myreversi

import myreversi.models.player.Player
import myreversi.models.reversi.Board

import myreversi.views.BoardView


import scala.scalajs.js
import org.scalajs.jquery.{JQuery, jQuery}

object ReversiApp extends js.JSApp {

  val Edge = 8

  def main(): Unit = refresh(Board.initialize(Edge, Player.Black))

  def refresh(board: Board): Unit =
    jQuery("#main").empty().append(BoardView(board).render())
}
