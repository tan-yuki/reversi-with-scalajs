package reversi.models

import reversi.service.ReversiCalculatorService

trait CellCollection extends Seq[Cell] {

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
   * (x,y) に位置するCellを取得
   *
   * @throws IllegalArgumentException
   *     (x,y)座標にCellが存在しない場合に投げられる例外
   *
   * @param x X-axis
   * @param y Y-axis
   * @return Cell
   */
  def get(x: Int, y:Int):Cell = this.find(x, y)
    .getOrElse(throw new IllegalArgumentException("Invalid x,y axis: ($x, $y)"))

  /**
   * Point(x,y) に位置するCellを取得
   *
   * @throws IllegalArgumentException
   *     (x,y)座標にCellが存在しない場合に投げられる例外
   *
   * @param p Point
   * @return Cell
   */
  def get(p: Point):Cell = this.get(p.x, p.y)

  /**
   * (x,y) に位置するCellを取得
   *
   * @param x X-axis
   * @param y Y-axis
   * @return Option[Cell]
   */
  def find(x: Int, y: Int):Option[Cell] = this.find(_.isPlacedAt(x, y))

  /**
   * Point(x,y) に位置するCellを取得
   *
   * @param p Point
   * @return Option[Cell]
   */
  def find(p: Point):Option[Cell] = this.find(p.x, p.y)

  /**
   * (x, y)のCellにReversiを追加
   *
   * @param x X-axis
   * @param y Y-axis
   * @param reversi 新たに追加するReversi
   * @return CellCollection
   */
  def addReversi(x:Int, y:Int, reversi: Reversi): CellCollection = {
    val newCell = get(x, y).addReversi(reversi)
    val newCellCollection = replaceCell(newCell)

    ReversiCalculatorService.calculate(reversi.color, newCell.point, newCellCollection)
  }

  /**
   * Point(x, y)のCellにReversiを追加
   *
   * @param p Point
   * @param reversi 新たに追加するReversi
   * @return CellCollection
   */
  def addReversi(p:Point, reversi: Reversi): CellCollection
    = addReversi(p.x, p.y, reversi)

}

object CellCollection {
  implicit def seqCellsToCellCollection(cells: Seq[Cell]): CellCollection = new CellCollection {
    override def length: Int = cells.length

    override def apply(idx: Int): Cell = cells(idx)

    override def iterator: Iterator[Cell] = cells.iterator
  }
}

