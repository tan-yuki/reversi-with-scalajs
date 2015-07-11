package reversi.views

import org.scalajs.jquery._
import reversi.ReversiApp
import reversi.models._

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
          val newCellCollection = cellCollection.addReversiAndCalculate(
            cell.point, reversi)

          ReversiApp.refresh(newCellCollection)
        }
    }

    elem
  }

}


