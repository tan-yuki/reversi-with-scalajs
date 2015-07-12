package myreversi.models

import myreversi.service.strategy.RandomStrategy
import myreversi.models.reversi.player.{Player, CPU}
import utest._

object CPUTest extends TestSuite {


  def tests = TestSuite {
    'CPUBlackはPlayer {
      assert(CPU.Black(new RandomStrategy()).isInstanceOf[Player])
    }
    'CPUWhiteはCPU {
      assert(CPU.White(new RandomStrategy()).isInstanceOf[Player])
    }
    'CPUBlackはCPU {
      assert(CPU.Black(new RandomStrategy()).isInstanceOf[CPU])
    }
    'CPUWhiteはCPU {
      assert(CPU.White(new RandomStrategy()).isInstanceOf[CPU])
    }

  }
}
