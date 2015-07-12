package myreversi.models.reversi.player

import myreversi.models.shared.Color

private[player] trait BlackPlayer extends Player {
  val color = Color.Black
}
