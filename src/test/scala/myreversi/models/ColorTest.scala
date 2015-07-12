package myreversi.models

import myreversi.models.shared.Color

import utest._

object ColorTest extends TestSuite {


  def tests = TestSuite {
    'Whiteの反対はBlack {
      assert(Color.Black.opposite == Color.White)
    }

    'Blackの反対はWhite {
      assert(Color.White.opposite == Color.Black)
    }

  }
}
