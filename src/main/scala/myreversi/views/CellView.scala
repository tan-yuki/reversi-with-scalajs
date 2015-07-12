package myreversi.views

import myreversi.models.player.Player
import myreversi.models.reversi.{Reversi, Cell, Board}
import org.scalajs.jquery._

import myreversi.ReversiApp
import myreversi.exception.UnknownException
import myreversi.service.exception.NotFoundReversibleReversiException

import scala.util.{Success, Failure}

case class CellView(cell: Cell,
                    board: Board) extends View {

  override protected[this] val elem: JQuery =
    jQuery(s"""<td class="cell"></td>""")

  override def render(): JQuery = {

    val player = board.currentPlayerState.player

    val elemWithCandidates = renderCandidates(elem, player)

    renderReversi(elemWithCandidates, player)
  }

  private def renderCandidates(elem: JQuery, currentPlayer: Player): JQuery = {
    val cellCollection = board.cellCollection
    val points = cellCollection.candidates(currentPlayer.color)

    cell match {
      case Cell(p, _) if points.contains(p) =>
        elem.append(s"""<div class="candidate"></div>""")
      case _ =>
    }

    elem
  }

  private def renderReversi(elem:JQuery, currentPlayer: Player):JQuery = {
    cell.reversi match {
      case Some(r) =>
        elem.append(ReversiView(r).render())
      case None =>
        elem.click { e: JQueryEventObject =>
          e.stopPropagation()

          val reversi = Reversi(currentPlayer.color)
          board.cellCollection.addReversiAndCalculate(cell.point, reversi) match {
            case Failure(e: NotFoundReversibleReversiException)
              =>
            /*
             * TODO:
             *   絶対に起こりえないから省略したいけど、省略するとwarningがでる
             *   どうすればいいのかよくわからない
             */
            case Failure(throwable)
              => throw new UnknownException(cause = throwable)
            case Success(newCellCollection)
              =>
                ReversiApp.refresh(board.copy(
                  cellCollection = newCellCollection,
                  currentPlayerState = board.currentPlayerState.nextState
                ))
          }

        }
    }

    elem
  }

}


