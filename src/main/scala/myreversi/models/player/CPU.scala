package myreversi.models.player

trait CPU extends Player {}

object CPU {
  case object Black extends BlackPlayer with CPU
  case object White extends WhitePlayer with CPU
}
