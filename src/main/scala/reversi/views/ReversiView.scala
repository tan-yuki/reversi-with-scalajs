package reversi.views

import org.scalajs.jquery._
import reversi.models.Reversi

case class ReversiView(reversi: Reversi) extends View {
  override def render(): JQuery = {
    val elem = jQuery(s"""<div class="reversi"></div>""")
    elem.addClass(reversi.color.code)

    elem
  }
}
