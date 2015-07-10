package reversi.models

case class Board(edge: Int) {
  require(edge >= 4 && edge % 2 == 0)
}
