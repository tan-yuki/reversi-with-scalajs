package myreversi.models.reversi.player

import myreversi.service.strategy.CPUStrategy


trait CPU extends Player {
  val strategy: CPUStrategy
}

object CPU {
  case class Black(strategy: CPUStrategy) extends BlackPlayer with CPU
  case class White(strategy: CPUStrategy) extends WhitePlayer with CPU
}
