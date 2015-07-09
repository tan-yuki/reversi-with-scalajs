package reversi.views

import org.scalajs.jquery._
import reversi.models.{Board, Cell}

case class BoardView(board: Board) extends View {

  override def render(): JQuery = {

    val cellRecords:Seq[JQuery] = (1 to board.edge).map( y => {
      val cells:Seq[JQuery] = (1 to board.edge).map( x => {
        CellView(Cell(x, y, None)).render()
      }).toSeq

      jQuery("<tr/>").append(cells: _*)
    }).toSeq


    jQuery(s"""<table class="board" />""").append(cellRecords: _*)
  }
}
