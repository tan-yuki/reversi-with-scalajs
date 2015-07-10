package reversi.views

import org.scalajs.jquery._
import reversi.models.{Reversi, Color, Board, Cell}

case class BoardView(board: Board) extends View {

  lazy private val initialReversiMapping: Map[(Int,Int), Reversi] = {
    val middle:Int = board.edge / 2

    Map(
      (middle,     middle)     -> Reversi(Color.Black),
      (middle + 1, middle)     -> Reversi(Color.White),
      (middle,     middle + 1) -> Reversi(Color.White),
      (middle + 1, middle + 1) -> Reversi(Color.Black)
    )
  }

  override protected[this] val elem: JQuery =
    jQuery(s"""<table class="board" />""")

  override def render(): JQuery = {

    println(initialReversiMapping)

    val cellRecords:Seq[JQuery] = (1 to board.edge).map( y => {
      val cells:Seq[JQuery] = (1 to board.edge).map( x => {

        val cellView:CellView = initialReversiMapping.get((x, y)) match {
          case Some(r) => CellView(Cell(x, y, Some(r)))
          case None => CellView(Cell(x, y, None))
        }

        cellView.render()

      }).toSeq

      jQuery("<tr/>").append(cells: _*)
    }).toSeq


    elem.append(cellRecords: _*)
  }
}
