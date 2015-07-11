package reversi.views

import org.scalajs.jquery._
import reversi.models.Board

case class BoardView(board: Board) extends View {


  override protected[this] val elem: JQuery =
    jQuery(s"""<table class="board" />""")

  override def render(): JQuery = {

    val recordsElem: Seq[JQuery] = (1 to board.edge).map { y =>
      // Create <tr> dom
      val recordElem = jQuery("<tr />")

      // Get cells fixed by "y"-axis
      val cellRecord = board.cellCollection.filter(_.point.y == y)

      // Append cell dom to <tr> elem
      recordElem.append(cellRecord.map(c => CellView(c, board.cellCollection).render()): _*)

      recordElem
    }.toSeq


    elem.append(recordsElem: _*)

    elem
  }
}

