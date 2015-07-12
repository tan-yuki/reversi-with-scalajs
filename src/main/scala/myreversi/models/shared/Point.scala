package myreversi.models.shared

/**
 * 1-originで盤面を以下のような座標とみなす.
 *
 * <code>
 *   +-----------------> X
 *   |     1   2   3
 *   |    -----------
 *   | 1 |   |   |   |
 *   |   |-----------|
 *   | 2 |   |   |   |
 *   |   |-----------|
 *   | 3 |   |   |   |
 *   |    -----------
 *   V
 *   Y
 * </code>
 *
 * @param x x軸座標
 * @param y y軸座標
 */
case class Point(x:Int, y:Int) {
  def move(v: Vector): Point = Point(x + v.x, y + v.y)
}

object Point {
  def at(x: Int, y:Int) = {
    require(x >= 1)
    require(y >= 1)

    Point(x, y)
  }
}
