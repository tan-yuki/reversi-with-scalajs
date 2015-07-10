package reversi.models

import utest._

object BoardTest extends TestSuite {

  def tests = TestSuite {

    'Boardは正方形である {
      val edge = 8
      val board = Board.initialize(edge)
      assert(board.cells.length == edge * edge)
    }

    '初期状態のBoardにReversiが4つ含まれている {
      val edge = 8
      val board = Board.initialize(edge)
      assert(board.cells.count(_.hasReversi) == 4)
    }

    'BoardにReversiを追加できる {
      val edge = 8
      val board = Board.initialize(edge)
      board.addReversi(1, 1, Reversi(Color.White))
    }

    '初期状態で黒のReversiが2つ置かれている {
      val edge = 8
      val board = Board.initialize(edge)
      board.cells.filter(_.hasReversiColoredBy(Color.Black))
    }

    '初期状態で黒のReversiが2つ置かれている {
      val edge = 8
      val board = Board.initialize(edge)
      board.cells.filter(_.hasReversiColoredBy(Color.White))
    }
  }
}
