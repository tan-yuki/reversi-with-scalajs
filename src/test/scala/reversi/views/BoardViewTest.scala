package reversi.views

import reversi.models.{Color, Board}
import utest._

object BoardViewTest extends TestSuite {

  val edge = 8
  val elem = BoardView(Board(edge)).render()

  def tests = TestSuite {
    'Boardが正方形である {
      assert(elem.find("td").length == edge * edge)
    }

    '初期状態でReversiが4つ置かれている {
      assert(elem.find(".reversi").length == 4)
    }

    '初期状態で黒のReversiが2つ置かれている {
      assert(elem.find("." + Color.Black.code).length == 2)
    }

    '初期状態で白のReversiが2つ置かれている {
      assert(elem.find("." + Color.White.code).length == 2)
    }
  }
}
