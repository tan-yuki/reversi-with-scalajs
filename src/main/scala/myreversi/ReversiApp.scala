package myreversi

import myreversi.models.reversi.player._
import myreversi.models.reversi.{CellCollection, Board}
import myreversi.service.strategy.RandomStrategy

import myreversi.views.BoardView


import scala.scalajs.js
import org.scalajs.jquery.{JQuery, jQuery}

object ReversiApp extends js.JSApp {

  val Edge = 8

  val playerPare: PlayerPare = PlayerPare(User.Black, CPU.White(new RandomStrategy()))

  def main(): Unit = refresh(Board.initialize(Edge, CurrentPlayerState.initialState(playerPare)))

  def refresh(board: Board): Unit =
    jQuery("#main").empty().append(BoardView(board).render())
}
