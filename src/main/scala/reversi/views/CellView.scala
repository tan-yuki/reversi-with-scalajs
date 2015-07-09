package reversi.views

import org.scalajs.jquery._
import reversi.models.{Color, Reversi, Cell}

import scala.scalajs.js

case class CellView(cell: Cell) extends View {

  override def render(): JQuery = {
    val elem = jQuery(s"""<td class="cell"></td>""")

    elem.click { () =>
      elem.append(ReversiView(Reversi(Color.Black)).render())
    }

    elem
  }
}


