package reversi.views

import org.scalajs.jquery._

import reversi.ReversiApp
import reversi.exception.UnknownException
import reversi.models._
import reversi.service.exception.NotFoundReversibleReversiException

import scala.util.{Success, Failure}

case class CellView(cell: Cell, cellCollection: CellCollection) extends View {

  override protected[this] val elem: JQuery =
    jQuery(s"""<td class="cell"></td>""")

  override def render(): JQuery = {

    val puttingColor = Color.Black

    val elemWithCandidates = renderCandidates(elem, puttingColor)

    renderReversi(elemWithCandidates, puttingColor)
  }

  private def renderCandidates(elem: JQuery, color: Color): JQuery = {
    val points = cellCollection.candidates(color)

    cell match {
      case Cell(p, _) if points.contains(p) =>
        elem.append(s"""<div class="candidate"></div>""")
      case _ =>
    }

    elem
  }

  private def renderReversi(elem:JQuery, color: Color):JQuery = {
    cell.reversi match {
      case Some(r) =>
        elem.append(ReversiView(r).render())
      case None =>
        elem.click { e: JQueryEventObject =>
          e.stopPropagation()

          val reversi = Reversi(color)
          cellCollection.addReversiAndCalculate(cell.point, reversi) match {
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
              => ReversiApp.refresh(newCellCollection)
          }

        }
    }

    elem
  }

}


