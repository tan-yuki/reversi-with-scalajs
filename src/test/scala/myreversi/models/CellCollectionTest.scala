package myreversi.models

import myreversi.ReversiApp
import myreversi.models.player.{User, PlayerPare, CurrentPlayerState, Player}
import myreversi.models.reversi.{Reversi, CellCollection, Cell, Board}
import myreversi.models.shared.{Color, Point}
import utest._

object CellCollectionTest extends TestSuite {

  def createInitialBoard(edge:Int = ReversiApp.Edge) = {
    Board.initialize(edge, CurrentPlayerState(User.Black, PlayerPare(User.Black, User.White)))
  }


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
      val initialCellCollection = createInitialBoard(edge = 8).cellCollection
      val newCellCollection = initialCellCollection.addReversiAndCalculate(
        Point(3, 4), Reversi(Color.White)).get

      assert(newCellCollection.count(_.hasReversi) == 5)
      assert(newCellCollection.count(_.hasReversiColoredBy(Color.Black)) == 1)
      assert(newCellCollection.count(_.hasReversiColoredBy(Color.White)) == 4)
      assert(newCellCollection.get(Point(4, 4)).hasReversiColoredBy(Color.White))
    }

    '初期状態のCellCollectionの場合黒のCandidatesは4つ {
      val initialCellCollection = createInitialBoard(edge = 8).cellCollection
      val candidates = initialCellCollection.candidates(Color.Black)

      assert(candidates.length == 4)
      assert(candidates.contains(Point(3, 5)))
      assert(candidates.contains(Point(4, 6)))
      assert(candidates.contains(Point(5, 3)))
      assert(candidates.contains(Point(6, 4)))
    }

    '初期状態のCellCollectionの場合白のCandidatesは4つ {
      val initialCellCollection = createInitialBoard(edge = 8).cellCollection
      val candidates = initialCellCollection.candidates(Color.White)

      assert(candidates.length == 4)
      assert(candidates.contains(Point(3, 4)))
      assert(candidates.contains(Point(4, 3)))
      assert(candidates.contains(Point(5, 6)))
      assert(candidates.contains(Point(6, 5)))
    }
  }
}
