package reversi.views

import reversi.models.Cell

case class CellView(cell: Cell) extends ViewInterface[String] {

  override def render(): String = {
    s"""<td class="cell"></td>"""
  }
}
