package reversi.views

import org.scalajs.jquery._
import reversi.ReversiApp
import reversi.models._

case class CellView(cell: Cell, cellCollection: CellCollection) extends View {

  override protected[this] val elem: JQuery =
    jQuery(s"""<td class="cell"></td>""")

  override def render(): JQuery = {

    cell.reversi match {
      case Some(r) =>
        elem.append(ReversiView(r).render())
      case None =>
          elem.click { e: JQueryEventObject =>
            e.stopPropagation()

            val reversi = Reversi(Color.Black)
            val newCellCollection = cellCollection.addReversi(cell.point, reversi)

            ReversiApp.refresh(newCellCollection)
          }
    }

    elem
  }

}


