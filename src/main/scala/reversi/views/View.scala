package reversi.views

import org.scalajs.jquery.JQuery

trait View {
  protected[this] val elem:JQuery

  def render(): JQuery

}
