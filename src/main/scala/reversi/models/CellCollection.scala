package reversi.models

trait CellCollection extends Seq[Cell] {
}

object CellCollection {
  implicit def seqCellsToCellCollection(cells: Seq[Cell]): CellCollection = new CellCollection {
    override def length: Int = cells.length

    override def apply(idx: Int): Cell = cells(idx)

    override def iterator: Iterator[Cell] = cells.iterator
  }
}

