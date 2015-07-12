package myreversi.service.strategy

import myreversi.exception.UnknownException
import myreversi.models.reversi.{Reversi, CellCollection}
import myreversi.models.reversi.player.{CPU, CurrentPlayerState}
import myreversi.models.shared.Point

import scala.util.{Success, Failure}

/**
 * TODO: models.player.CPUからしかインスタンスを生成できないようにしたい
 */
trait CPUStrategy {

  /**
   * CPUのStrategyに基づき、Reversiを置く
   *
   * @throws UnknownException
   *     selectReversiPointの実装ミスでcandidates以外のPointが渡された時に投げられるエラー
   *
   * @param currentPlayer CPU
   * @param cellCollection Current cell collection
   * @return
   */
  def toggleReversi(currentPlayer: CPU,
                    cellCollection: CellCollection): CellCollection = {
    val point = selectAddingReversiPoint(currentPlayer, cellCollection)

    val tryToAddReversi = cellCollection.addReversiAndCalculate(point, Reversi(currentPlayer.color))

    tryToAddReversi match {
      case Failure(e) => throw new UnknownException(cause = e.getCause)
      case Success(c:CellCollection) => c
    }
  }

  protected def selectAddingReversiPoint(currentPlayer: CPU,
                                         cellCollection: CellCollection): Point
}
