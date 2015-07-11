package reversi.models

import utest._

object CellCollectionTest extends TestSuite {


  def tests = TestSuite {
    'CellのSequenceをCellCollectionへ変換可能 {
      val cells: CellCollection = Seq(
        Cell(1, 1, Some(Reversi(Color.White))),
        Cell(1, 2, Some(Reversi(Color.Black))),
        Cell(1, 3, None)
      )

      assert(cells.foldLeft[Int](0) { _ + _.y } == 6)
    }
  }
}
