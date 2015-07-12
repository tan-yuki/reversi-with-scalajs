package myreversi.models.player

trait User extends Player {}

object User {
  case object Black extends BlackPlayer with User
  case object White extends WhitePlayer with User
}


