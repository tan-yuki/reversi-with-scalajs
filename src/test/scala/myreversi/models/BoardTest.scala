package myreversi.models

import myreversi.ReversiApp
import myreversi.models.player.{CurrentPlayerState, PlayerPare, User}
import myreversi.models.reversi.Board
import myreversi.models.shared.Color
import utest._

object BoardTest extends TestSuite {

  def createInitialBoard(edge:Int = ReversiApp.Edge) = {
    Board.initialize(edge, CurrentPlayerState(User.Black, PlayerPare(User.Black, User.White)))
  }

  def tests = TestSuite {

    'Boardは正方形である {
      val edge = 8
      val board = createInitialBoard(edge = edge)
      assert(board.cellCollection.length == edge * edge)
    }

    '初期状態のBoardにReversiが4つ含まれている {
      val board = createInitialBoard()
      assert(board.cellCollection.count(_.hasReversi) == 4)
    }

    '初期状態で黒のReversiが2つ置かれている {
      val board = createInitialBoard()
      assert(board.cellCollection.count(_.hasReversiColoredBy(Color.Black))
        == 2)
    }

    '初期状態で白のReversiが2つ置かれている {
      val board = createInitialBoard()
      assert(board.cellCollection.count(_.hasReversiColoredBy(Color.White))
        == 2)
    }
  }
}
