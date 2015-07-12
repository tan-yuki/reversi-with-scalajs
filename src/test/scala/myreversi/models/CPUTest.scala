package myreversi.models

import myreversi.models.reversi.player.{Player, CPU}
import utest._

object CPUTest extends TestSuite {


  def tests = TestSuite {
    'CPUBlackはPlayer {
      assert(CPU.Black.isInstanceOf[Player])
    }
    'CPUWhiteはCPU {
      assert(CPU.White.isInstanceOf[Player])
    }
    'CPUBlackはCPU {
      assert(CPU.Black.isInstanceOf[CPU])
    }
    'CPUWhiteはCPU {
      assert(CPU.White.isInstanceOf[CPU])
    }

  }
}
