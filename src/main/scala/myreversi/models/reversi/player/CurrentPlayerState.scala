package myreversi.models.reversi.player

case class CurrentPlayerState(player: Player, playerPare: PlayerPair) {

  lazy val nextState: CurrentPlayerState = copy(
    player = playerPare.oppositePlayer(player)
  )

  lazy val color = player.color
}

object CurrentPlayerState {
  def initialState(playerPair: PlayerPair): CurrentPlayerState =
    CurrentPlayerState(playerPair.firstPlayer, playerPair)
}
