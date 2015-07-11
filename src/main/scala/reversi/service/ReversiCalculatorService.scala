package reversi.service

import reversi.models._

/**
 * Reversi裏返しのロジック
 */
object ReversiCalculatorService {

  private val vectors:Seq[Vector] = Seq(
    Vector(-1, -1), Vector(-1, 0), Vector(-1, 1),
    Vector(0, -1),  Vector(0, 0),  Vector(0, 1),
    Vector(1, -1),  Vector(1, 0),  Vector(1, 1)
  )

  /**
   * 新たに追加されたReversiを元に既存のReversiの状態を変更する
   * 同じ色で挟み込まれたReversiの裏返しを実行
   *
   * @param color 新たに追加されたReversiの色
   * @param point 新たに追加されたReversiの場所
   * @param currentCellCollection 現在のCellCollectionの状態（新たにReversiを追加した状態）
   * @return 新しい状態のCellCollection
   */
  def calculate(color: Color, point: Point, currentCellCollection: CellCollection): CellCollection = {
    val cell = currentCellCollection.find(point)

    require(cell.isDefined && cell.get.hasReversiColoredBy(color))

    val points = vectors.flatMap { v =>
      searchToggleReversi(color, point, v, currentCellCollection)
    }

    currentCellCollection.map {
      case c if points.contains(c.point) => c.toggleReversi()
      case c => c
    }
  }

  /**
   * Vector方向に裏返せるReversiを検索する。
   * 裏返せるReversiが見つかった場合はそのPointのリストを返す　
   *
   * @param color 追加したReversiの色
   * @param point 追加したReversiのPoint
   * @param vector 裏返せるReversiを検索する方向
   * @param collection 現在のCellCollection
   *
   * @return Seq[Point]
   */
  private def searchToggleReversi(color: Color,
                                  point: Point,
                                  vector: Vector,
                                  collection: CellCollection) : Seq[Point] = {

    def go(acc:List[Point], point: Point):List[Point] = {
      val newPoint = point.move(vector)

      collection.find(newPoint) match {

        // 存在しないCellまで検索した場合
        case None => List.empty

        // Cellが存在した場合はReversiを調べる
        case Some(cell) => cell.reversi match {

          // Reversiが存在しない場合は裏返せるものがない
          case None => List.empty

          // 裏返すものと同じ色のReversiが見つかった場合、その時点で検索終了
          case Some(Reversi(c)) if color == c => acc

          // 裏返すものと別の色のReversiが見つかった場合、検索続行
          case _ => go(cell.point :: acc, newPoint)
        }
      }
    }

    go(List.empty, point).toSeq
  }

}
