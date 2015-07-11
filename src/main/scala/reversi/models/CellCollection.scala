package reversi.models

import reversi.service.ReversiCalculatorService

import scala.util.Try

trait CellCollection extends Seq[Cell] {

  private lazy val _candidates = Map(
    Color.Black.code -> ReversiCalculatorService.searchCandidate(Color.Black, this),
    Color.White.code -> ReversiCalculatorService.searchCandidate(Color.White, this)
  )

  /**
   * 指定した色が置けるPointのリストを返す
   *
   * @param color Color
   * @return
   */
  def candidates(color: Color): Seq[Point] = _candidates.get(color.code).get

  /**
   * 渡されたCellに位置するCellを置き換える
   *
   * @param cell Cell
   * @return CellCollection
   */
  def replaceCell(cell: Cell): CellCollection = map {
    case c if c.isPlacedAt(cell.point) => cell
    case c => c
  }

  /**
   * Point(x,y) に位置するCellを取得
   *
   * @throws IllegalArgumentException
   *     (x,y)座標にCellが存在しない場合に投げられる例外
   *
   * @param p Point
   * @return Cell
   */
  def get(p: Point):Cell = this.find(p)
    .getOrElse(throw new IllegalArgumentException("Invalid x,y axis: ($x, $y)"))

  /**
   * Point(x,y) に位置するCellを取得
   *
   * @param p Point
   * @return Option[Cell]
   */
  def find(p: Point):Option[Cell] = this.find(_.isPlacedAt(p.x, p.y))

  /**
   * Point(x, y)のCellにReversiを追加し、裏返しを実行
   *
   * @param p Point
   * @param reversi 新たに追加するReversi
   * @return CellCollection
   */
  def addReversiAndCalculate(p:Point, reversi: Reversi): Try[CellCollection] =
    ReversiCalculatorService.addReversi(reversi, p, this)

}

object CellCollection {
  implicit def seqCellsToCellCollection(cells: Seq[Cell]): CellCollection = new CellCollection {
    override def length: Int = cells.length

    override def apply(idx: Int): Cell = cells(idx)

    override def iterator: Iterator[Cell] = cells.iterator
  }
}

