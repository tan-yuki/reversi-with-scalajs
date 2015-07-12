package myreversi.service

import myreversi.service.exception.NotFoundReversibleReversiException
import myreversi.models.reversi.{Reversi, CellCollection, Cell}
import myreversi.models.shared.{Color, Point}
import myreversi.models._

import scala.util.{Success, Failure, Try}

/**
 * Reversi裏返しのロジック
 */
object ReversiCalculatorService {

  private val vectors:Seq[shared.Vector] = Seq(
    shared.Vector(-1, -1), shared.Vector(-1, 0), shared.Vector(-1, 1),
    shared.Vector(0, -1),  shared.Vector(0, 0),  shared.Vector(0, 1),
    shared.Vector(1, -1),  shared.Vector(1, 0),  shared.Vector(1, 1)
  )

  /**
   * 渡された色のReversiが置けるPointのリストを返す。
   * Reversiが置ける条件は、一つでも裏返せるReversiが存在する場所。
   * 
   * @param color Color
   * @param currentCellCollection CellCollection
   *                              
   * @return Pointのリスト
   */
  def searchCandidate(color: Color,
                      currentCellCollection: CellCollection): Seq[Point] = {

    currentCellCollection.foldLeft[List[Point]](List.empty) {
      (acc: List[Point], cell: Cell) =>
        cell match {
          case c if c.hasReversi => acc
          case c =>
            val reversiblePoints = searchReversiblePoints(color, cell.point, currentCellCollection)

            if (reversiblePoints.isEmpty) acc else cell.point :: acc
        }
    }.toSeq
  }

  /**
   * 新たにReversiを追加し、追加したReversiを元に既存のReversiの状態を変更する
   * 同じ色で挟み込まれたReversiの裏返しを実行
   *
   * @throws NotFoundReversibleReversiException
   *     Reversiを置こうとした箇所に置いても裏返せる他のReversiがない場合に投げられる
   *
   * @param reversi 新たに追加するReversi
   * @param point 新たに追加するReversiの場所
   * @param cellCollection 現在のCellCollectionの状態
   * @return Reversiを追加し裏返しを行った状態のCellCollection
   */
  def addReversi(reversi: Reversi, point: Point, cellCollection: CellCollection): Try[CellCollection] = {
    val cell = cellCollection.find(point)

    require(cell.isDefined && !cell.get.hasReversi)

    // 置けるPointがない場合は失敗とみなす
    val points = searchReversiblePoints(reversi.color, point, cellCollection)

    points match {
      case p if p.isEmpty =>
        Failure(new NotFoundReversibleReversiException)
      case _ =>
        // 指定された箇所にReversiを置く
        val newCollection = cellCollection.replaceCell(Cell(point, Some(reversi)))

        // 裏返して返す
        Success(newCollection.map {
          case c if points.contains(c.point) => c.toggleReversi()
          case c => c
        })
        
    }
  }

  /**
   * 裏返せるReversiを全方角で検索する。
   * 裏返せるReversiが見つかった場合はそのPointのリストを返す。
   *
   * @param color Reversiの色。
   *              この色に関する裏返しを行えるPointを探す。
   * @param point 起点となるPoint。
   *              このPointに指定された色が置かれた際の裏返されるPointを探す。
   * @param collection 現在のCellCollection
   * @return Seq[Point]
   */
  private def searchReversiblePoints(color: Color,
                                  point: Point,
                                  collection: CellCollection) : Seq[Point] = {
    vectors.flatMap { v =>
      searchReversiblePointsWithVector(v, color, point, collection)
    }
  }

  /**
   * Vector方向に裏返せるReversiを検索する。
   * 裏返せるReversiが見つかった場合はそのPointのリストを返す　
   *
   * @param vector 裏返せるReversiを検索する方向。
   * @param color Reversiの色。
   *              この色に関する裏返しを行えるPointを探す。
   * @param point 起点となるPoint。
   *              このPointに指定された色が置かれた際の裏返されるPointを探す。
   * @param collection 現在のCellCollection
   *
   * @return Seq[Point]
   */
  private def searchReversiblePointsWithVector(vector: shared.Vector,
                                  color: Color,
                                  point: Point,
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
