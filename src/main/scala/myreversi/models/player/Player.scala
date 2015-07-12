package myreversi.models.player

import myreversi.models.shared.Color


sealed abstract class Player(val color: Color) {
  def opposite: Player = this match {
    case Player.Black => Player.White
    case Player.White => Player.Black
  }
}

object Player {
  case object Black extends Player(Color.Black)
  case object White extends Player(Color.White)
}


