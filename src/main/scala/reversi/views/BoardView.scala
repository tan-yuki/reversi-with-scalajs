package reversi.views

import reversi.models.{Board, Cell}

case class BoardView(board: Board) extends ViewInterface[String] {

  override def render() = {

    val cellRecords:Seq[String] = (1 to board.edge).map( y => {
      val cells:Seq[String] = (1 to board.edge).map( x => {
        CellView(Cell(x, y)).render()
      }).toSeq

      s"<tr>${cells.mkString}</tr>"
    })


    s"""<table>${cellRecords.mkString}</table>"""
  }
}
