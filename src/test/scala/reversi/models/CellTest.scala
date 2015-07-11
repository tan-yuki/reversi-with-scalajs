package reversi.models

import utest._

object CellTest extends TestSuite {


  def tests = TestSuite {
    'ReversiをもっているCellの判定 {
      val cell = Cell(Point(1, 1), Some(Reversi(Color.White)))

      assert(cell.hasReversi)
      assert(!cell.hasReversiColoredBy(Color.Black))
      assert(cell.hasReversiColoredBy(Color.White))
    }

    'ReversiをもっていないCellの判定 {
      val cell = Cell(Point(1, 1), None)

      assert(!cell.hasReversi)
      assert(!cell.hasReversiColoredBy(Color.Black))
      assert(!cell.hasReversiColoredBy(Color.White))
    }
  }
}
