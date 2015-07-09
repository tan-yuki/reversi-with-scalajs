package reversi.models

sealed abstract class Color(val code:String)
object Color {
  case object White extends Color("white")
  case object Black extends Color("black")
}


