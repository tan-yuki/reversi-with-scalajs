package reversi

import reversi.models.Board

import reversi.views.BoardView


import scala.scalajs.js
import org.scalajs.jquery.jQuery

object ReversiApp extends js.JSApp {

  def main(): Unit = {
    jQuery("#main").append(BoardView(Board.initialize(8)).render())
  }
}
