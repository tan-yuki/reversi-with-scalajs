package reversi.views

import reversi.ReversiApp
import reversi.models.{Reversi, Color, Board}

import utest._
import org.scalajs.jquery._

object ReversiStoryTest extends TestSuite {

  jQuery("body").append(s"""<div id="main"></div>""")
  ReversiApp.refresh(Board.initialize(ReversiApp.Edge))

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

    'Reversiが置かれていないCellをクリックするとReversiが置かれる {
      val cellElem = elem.find("tr:eq(3) > td:eq(2)")
      cellElem.click()
      assert(elem.find(".reversi").length == 5)
    }

    'Reversiが置かれているCellをクリックしてもなにも変わらない {
      val cellElem = elem.find("tr:eq(3) > td:eq(3)")
      val count = elem.find(".reversi").length
      cellElem.click()
      assert(elem.find(".reversi").length == count)
    }
  }
}
