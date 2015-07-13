package myreversi.integration

import myreversi.ReversiApp

import myreversi.models.reversi.Board
import myreversi.models.reversi.player.{CPU, CurrentPlayerState, PlayerPare, User}
import myreversi.service.strategy.RandomStrategy
import myreversi.views.BoardView
import org.scalajs.jquery._
import utest._
import utest.framework.Result
import utest.util.Tree

import scala.concurrent.{Future, Await}
import scala.scalajs.js.timers
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

object UserVsCPUStoryTest extends TestSuite {

  jQuery("body").append(s"""<div id="main"></div>""")

  val elem = jQuery("#main")
  val edge = ReversiApp.Edge
  val interval = 1

  // 初期状態用意
  val playerPare = PlayerPare(User.Black, CPU.White(new RandomStrategy()))
  val state = CurrentPlayerState.initialState(playerPare)
  val view = BoardView(Board.initialize(edge, state), interval)
  elem.append(view.render())

  val reversiCount = elem.find(".reversi").length
  val blackCount = elem.find(".black").length
  val whiteCount = elem.find(".white").length

  // 最初に黒を置く
  elem.find(".candidate:eq(0)").click()

  val tests = TestSuite {
    '黒を置いてしばらくすると自動的に白が置かれる {
      // TODO: setTimeoutのテストどうやるのかわからん
      /*
      assert(elem.find(".reversi").length == reversiCount + 1)
      assert(elem.find(".black").length < blackCount)
      assert(elem.find(".white").length >= whiteCount + 2)
      */
    }
  }
}
