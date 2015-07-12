package myreversi

import myreversi.models.reversi.player.{CurrentPlayerState, User, PlayerPare, Player}
import myreversi.models.reversi.Board

import myreversi.views.BoardView


import scala.scalajs.js
import org.scalajs.jquery.{JQuery, jQuery}

object ReversiApp extends js.JSApp {

  val Edge = 8

  val playerPare: PlayerPare = PlayerPare(User.Black, User.White)

  def main(): Unit = refresh(Board.initialize(Edge, CurrentPlayerState.initialState(playerPare)))

  def refresh(board: Board): Unit =
    jQuery("#main").empty().append(BoardView(board).render())
}
