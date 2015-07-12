package myreversi.views

import org.scalajs.jquery._
import myreversi.models.reversi.Reversi

case class ReversiView(reversi: Reversi) extends View {
  override protected[this] val elem: JQuery =
    jQuery(s"""<div class="reversi"></div>""")

  override def render(): JQuery = {
    elem.addClass(reversi.color.code)

    elem
  }

}
