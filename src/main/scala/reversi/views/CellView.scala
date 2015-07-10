package reversi.views

import org.scalajs.jquery._
import reversi.models.{Color, Reversi, Cell}

case class CellView(cell: Cell) extends View {

  override protected[this] val elem: JQuery =
    jQuery(s"""<td class="cell"></td>""")

  override def render(): JQuery = {

    cell.reversi match {
      case Some(r) =>
        elem.append(ReversiView(r).render())
      case None =>
          elem.click { e: JQueryEventObject =>
            e.stopPropagation()
            elem.append(ReversiView(Reversi(Color.Black)).render())
          }
    }

    elem
  }

}


