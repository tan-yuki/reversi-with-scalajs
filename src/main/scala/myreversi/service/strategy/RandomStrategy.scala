package myreversi.service.strategy

import myreversi.models.reversi.CellCollection
import myreversi.models.reversi.player.{CPU, CurrentPlayerState}
import myreversi.models.shared.Point

import scala.util.Random

class RandomStrategy extends CPUStrategy {
  override protected def selectAddingReversiPoint(currentPlayer: CPU,
                                                  cellCollection: CellCollection): Point = {

    val color = currentPlayer.color
    val points = cellCollection.candidates(color)
    Random.shuffle(points).head
  }
}

