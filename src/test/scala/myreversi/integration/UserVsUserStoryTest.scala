package myreversi.integration

import myreversi.ReversiApp
import myreversi.models.reversi.Board
import myreversi.models.reversi.player.{CurrentPlayerState, PlayerPare, User}
import org.scalajs.jquery._
import utest._

object UserVsUserStoryTest extends TestSuite {

  jQuery("body").append(s"""<div id="main"></div>""")

  ReversiApp.refresh(Board.initialize(
    ReversiApp.Edge,
    CurrentPlayerState.initialState(PlayerPare(User.Black, User.White))))

  val elem = jQuery("#main")
  val edge = ReversiApp.Edge

  // 最初に黒を置く
  elem.find(".candidate:eq(0)").click()

  def tests = TestSuite {
    '黒を置いた次にCandidateのCellをクリックすると白のReversiが置かれる {
      val cellElem = elem.find(".candidate:eq(0)")
      val reversiCount = elem.find(".reversi").length
      val blackCount = elem.find(".black").length
      val whiteCount = elem.find(".white").length
      cellElem.click()
      assert(elem.find(".reversi").length == reversiCount + 1)
      assert(elem.find(".black").length < blackCount)
      assert(elem.find(".white").length >= whiteCount + 2)
    }
  }
}
