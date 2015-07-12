package myreversi.models.player

import myreversi.models.shared.Color

private[player] trait WhitePlayer extends Player {
  val color = Color.White
}
