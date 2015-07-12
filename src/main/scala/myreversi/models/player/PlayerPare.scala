package myreversi.models.player

case class PlayerPare(blackPlayer: BlackPlayer, whitePlayer: WhitePlayer) {
  val firstPlayer = blackPlayer

  def oppositePlayer(player: Player):Player = player match {
    case p if p == blackPlayer => whitePlayer
    case p if p == whitePlayer => blackPlayer
    case _ => throw new IllegalArgumentException("Wrong player: " + player.toString)
  }
}
