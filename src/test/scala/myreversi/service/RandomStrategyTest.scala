package myreversi.service

import myreversi.ReversiApp
import myreversi.models.reversi.player.{CPU, CurrentPlayerState, PlayerPair, User}
import myreversi.models.reversi.{Board, Cell, CellCollection, Reversi}
import myreversi.models.shared.{Color, Point}
import myreversi.service.strategy.RandomStrategy
import utest._

object RandomStrategyTest extends TestSuite {

  def createInitialBoard(cpu: CPU.Black) = {
    Board.initialize(ReversiApp.Edge,
      CurrentPlayerState(
        User.Black,
        PlayerPair(cpu, User.White)
      ))
  }


  def tests = TestSuite {
    'ランダムに一つReversiを置く {
      val strategy = new RandomStrategy
      val cpu = CPU.Black(strategy)
      val newCellCollection = cpu.strategy.toggleReversi(cpu, createInitialBoard(cpu).cellCollection)

      assert(newCellCollection.count(_.hasReversiColoredBy(Color.Black)) == 4)
      assert(newCellCollection.count(_.hasReversiColoredBy(Color.White)) == 1)
    }
  }
}
