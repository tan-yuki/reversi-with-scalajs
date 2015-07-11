package reversi.models

sealed abstract class Color(val code:String) {
  def opposite: Color = this match {
    case Color.White => Color.Black
    case Color.Black => Color.White
  }

}

object Color {
  case object White extends Color("white")
  case object Black extends Color("black")
}


