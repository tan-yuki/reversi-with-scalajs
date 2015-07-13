package myreversi.views

import myreversi.ReversiApp
import myreversi.models.reversi.Board
import myreversi.models.reversi.player.{CurrentPlayerState, PlayerPare, User}
import myreversi.models.shared.Color
import org.scalajs.jquery._
import utest._

object BoardViewTest extends TestSuite {
  jQuery("body").append(s"""<div id="main"></div>""")

  ReversiApp.refresh(Board.initialize(
    ReversiApp.Edge,
    CurrentPlayerState.initialState(PlayerPare(User.Black, User.White))))

  val elem = jQuery("#main")
  val edge = ReversiApp.Edge

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

    '初期状態でCandidateが4つある {
      assert(elem.find(".candidate").length == 4)
    }

    'CandidateでないCellをクリックしてもReversiが置かれない {
      val cellElem = elem.find("tr:eq(0) > td:eq(0)")
      val count = elem.find(".reversi").length
      cellElem.click()
      assert(elem.find(".reversi").length == count)
    }

    'CandidateのCellをクリックすると黒のReversiが置かれる {
      val cellElem = elem.find(".candidate:eq(0)")
      val reversiCount = elem.find(".reversi").length
      val blackCount = elem.find(".black").length
      val whiteCount = elem.find(".white").length
      cellElem.click()
      assert(elem.find(".reversi").length == reversiCount + 1)
      assert(elem.find(".black").length == blackCount + 2)
      assert(elem.find(".white").length < whiteCount)
    }

  }
}
