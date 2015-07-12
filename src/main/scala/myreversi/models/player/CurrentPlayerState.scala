package myreversi.models.player

import myreversi.models.reversi.Reversi

case class CurrentPlayerState(player: Player, playerPare: PlayerPare) {

  lazy val nextState: CurrentPlayerState = copy(
    player = playerPare.oppositePlayer(player)
  )

  lazy val reversi = Reversi(player.color)
}

object CurrentPlayerState {
  def initialState(playerPare: PlayerPare): CurrentPlayerState =
    CurrentPlayerState(playerPare.firstPlayer, playerPare)
}
