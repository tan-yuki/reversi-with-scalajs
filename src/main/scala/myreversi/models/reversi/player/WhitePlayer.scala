package myreversi.models.reversi.player

import myreversi.models.shared.Color

private[player] trait WhitePlayer extends Player {
  val color = Color.White
}
