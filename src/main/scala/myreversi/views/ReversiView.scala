package myreversi.views

import myreversi.models.reversi.Reversi
import org.scalajs.jquery._

case class ReversiView(reversi: Reversi) extends View {
  override protected[this] val elem: JQuery =
    jQuery(s"""<div class="reversi"></div>""")

  override def render(): JQuery = {
    elem.addClass(reversi.color.code)

    elem
  }

}
