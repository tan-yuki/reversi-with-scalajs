package myreversi.views

import myreversi.ReversiApp
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import myreversi.models.reversi.Board
import myreversi.models.reversi.player.{CPU, User}
import org.scalajs.jquery._

import scala.scalajs.js.timers

case class BoardView(board: Board, cpuIntervalTime: Int = BoardView.CPUActionInterval) extends View {

  override protected[this] val elem: JQuery =
    jQuery(s"""<table class="board" />""")

  override def render(): JQuery = {

    val recordsElem: Seq[JQuery] = (1 to board.edge).map { y =>

      // Get cells fixed by "y"-axis
      val cellRecord = board.cellCollection.filter(_.point.y == y)

      // Create cell elements
      val recordCellsElem:Seq[JQuery] = cellRecord.map(c => CellView(c, board).render())

      // Append cell dom to <tr> elem
      jQuery("<tr />").append(recordCellsElem: _*)
    }.toSeq

    elem.append(recordsElem: _*)

    // Attach cpu event
    val playerState = board.currentPlayerState

    playerState.player match {
      case u:User => elem
      case c:CPU  =>

        timers.setTimeout(cpuIntervalTime) {
          val newCellCollection = c.strategy.toggleReversi(c, board.cellCollection)

          ReversiApp.refresh(board.copy(
            cellCollection = newCellCollection,
            currentPlayerState = playerState.nextState
          ))
        }
        elem
    }
  }
}

object BoardView {
  val CPUActionInterval = 1000
}
