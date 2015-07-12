package myreversi.views

import org.scalajs.jquery._
import myreversi.models.reversi.Board

case class BoardView(board: Board) extends View {


  override protected[this] val elem: JQuery =
    jQuery(s"""<table class="board" />""")

  override def render(): JQuery = {

    val recordsElem: Seq[JQuery] = (1 to board.edge).map { y =>

      // Get cells fixed by "y"-axis
      val cellRecord = board.cellCollection.filter(_.point.y == y)

      // Create cell elements
      val recordCellsElem:Seq[JQuery] = cellRecord.map(c => CellView(c, board).render())

      // Append cell dom to <tr> elem
      jQuery("<tr />").append(recordCellsElem: _*)
    }.toSeq


    elem.append(recordsElem: _*)

    elem
  }
}

