package reversi.models

import utest._

object CellCollectionTest extends TestSuite {


  def tests = TestSuite {
    'CellのSequenceをCellCollectionへ変換可能 {
      val cells: CellCollection = Seq(
        Cell(Point(1, 1), Some(Reversi(Color.White))),
        Cell(Point(1, 2), Some(Reversi(Color.Black))),
        Cell(Point(1, 3), None)
      )

      assert(cells.foldLeft[Int](0) { _ + _.point.y } == 6)
    }

    '初期状態のCellCollectionのx3y4の位置に白Reversiを置くとx4y4のReversiが白になる {
      val initialCellCollection = Board.initialize(8).cellCollection
      val newCellCollection = initialCellCollection.addReversi(Point(3, 4), Reversi(Color.White))

      assert(newCellCollection.count(_.hasReversi) == 5)
      assert(newCellCollection.count(_.hasReversiColoredBy(Color.Black)) == 1)
      assert(newCellCollection.count(_.hasReversiColoredBy(Color.White)) == 4)
      assert(newCellCollection.get(4, 4).hasReversiColoredBy(Color.White))
    }
  }
}
