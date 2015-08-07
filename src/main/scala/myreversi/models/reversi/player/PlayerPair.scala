package myreversi.models.reversi.player

case class PlayerPair(blackPlayer: BlackPlayer, whitePlayer: WhitePlayer) {
  val firstPlayer = blackPlayer

  def oppositePlayer(player: Player):Player = player match {
    case p if p == blackPlayer => whitePlayer
    case p if p == whitePlayer => blackPlayer
    case _ => throw new IllegalArgumentException("Wrong player: " + player.toString)
  }
}
