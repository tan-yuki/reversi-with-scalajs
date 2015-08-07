package myreversi

import myreversi.models.reversi.Board
import myreversi.models.reversi.player._
import myreversi.service.strategy.RandomStrategy
import myreversi.views.BoardView
import org.scalajs.jquery._

import scala.scalajs.js

object ReversiApp extends js.JSApp {

  val Edge = 8

  val playerPair: PlayerPair = PlayerPair(User.Black, CPU.White(new RandomStrategy()))

  def main(): Unit = refresh(Board.initialize(Edge, CurrentPlayerState.initialState(playerPair)))

  def refresh(board: Board): Unit =
    jQuery("#main").empty().append(BoardView(board).render())
}
