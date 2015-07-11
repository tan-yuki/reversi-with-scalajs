package reversi.models

import utest._

object PlayerTest extends TestSuite {


  def tests = TestSuite {
    'Whiteの反対はBlack {
      assert(Player.Black.opposite == Player.White)
    }

    'Blackの反対はWhite {
      assert(Player.White.opposite == Player.Black)
    }

  }
}
