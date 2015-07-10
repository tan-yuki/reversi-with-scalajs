package reversi.views

import reversi.models.{Color, Reversi, Cell}
import utest._

object CellViewTest extends TestSuite {


  def tests = TestSuite {
    'Reversiが置かれていないCellをクリックするとReversiが置かれる {
      val elem = CellView(Cell(1, 2, None)).render()
      elem.click()
      assert(elem.find(".reversi").length == 1)
    }

    'Reversiが置かれているCellをクリックしてもなにも変わらない {
      val reversi = Reversi(Color.White)
      val elem = CellView(Cell(1, 2, Some(reversi))).render()
      assert(elem.find(".reversi").length == 1)
      elem.click()
      assert(elem.find(".reversi").length == 1)
      assert(elem.find(".white").length == 1)
    }
  }
}
