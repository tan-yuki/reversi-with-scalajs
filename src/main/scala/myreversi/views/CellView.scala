package myreversi.views

import myreversi.service.strategy.CPUStrategy
import myreversi.models.reversi.player.{CPU, User, CurrentPlayerState, Player}
import myreversi.models.reversi.{Reversi, Cell, Board}
import myreversi.models.shared.Color
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

    val currentPlayerState = board.currentPlayerState

    val elemWithCandidates = renderCandidates(elem, currentPlayerState)

    renderReversi(elemWithCandidates, currentPlayerState)
  }

  private def renderCandidates(elem: JQuery, currentPlayerState: CurrentPlayerState): JQuery = {
    val cellCollection = board.cellCollection
    val points = cellCollection.candidates(currentPlayerState.player.color)

    cell match {
      case Cell(p, _) if points.contains(p) =>
        elem.append(s"""<div class="candidate"></div>""")
      case _ =>
    }

    elem
  }

  private def renderReversi(elem:JQuery, currentPlayerState: CurrentPlayerState):JQuery = {
    cell.reversi match {
      case Some(r) =>
        elem.append(ReversiView(r).render())
      case None =>
        attachPuttingReversiEvent(elem, currentPlayerState)
    }
  }

  /**
   * Cellの要素にクリックイベント付与
   *
   * @param elem jQuery cell element
   * @param currentPlayerState 現在のPlayerの状態
   * @return jQuery cell element attached click event.
   */
  private def attachPuttingReversiEvent(elem: JQuery, currentPlayerState: CurrentPlayerState): JQuery = {
    currentPlayerState match {
      case CurrentPlayerState(u: User, _) => attachPuttingReversiByUserEvent(elem, u.color)
      case CurrentPlayerState(c: CPU, _) => elem
    }
  }

  /**
   * Userのクリックイベントをバインド
   *
   * @param elem jQuery cell element
   * @param color Userがクリックした時に置くReversiの色
   * @return jQuery cell element attached click event by user.
   */
  private def attachPuttingReversiByUserEvent(elem:JQuery, color: Color):JQuery = {
    elem.click { e: JQueryEventObject =>
      e.stopPropagation()

      board.cellCollection.addReversiAndCalculate(cell.point, Reversi(color)) match {
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
}


