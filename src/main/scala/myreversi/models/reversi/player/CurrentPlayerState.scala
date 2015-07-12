package myreversi.models.reversi.player

case class CurrentPlayerState(player: Player, playerPare: PlayerPare) {

  lazy val nextState: CurrentPlayerState = copy(
    player = playerPare.oppositePlayer(player)
  )

  lazy val color = player.color
}

object CurrentPlayerState {
  def initialState(playerPare: PlayerPare): CurrentPlayerState =
    CurrentPlayerState(playerPare.firstPlayer, playerPare)
}
