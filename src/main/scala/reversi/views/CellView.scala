package reversi.views

import org.scalajs.jquery._
import reversi.models.{Color, Reversi, Cell}

import scala.scalajs.js

case class CellView(cell: Cell) extends View {

  override protected[this] def render(): JQuery = {
    jQuery(s"""<td class="cell"></td>""")
  }

  override def renderWithEvents(): JQuery = {
    val elem:JQuery = render()

    elem.click { () =>
      elem.append(ReversiView(Reversi(Color.Black)).renderWithEvents())
    }

    elem
  }
}


