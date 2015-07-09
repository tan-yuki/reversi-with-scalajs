package reversi.views

import org.scalajs.jquery.JQuery

trait View {

  protected[this] def render(): JQuery

  def renderWithEvents(): JQuery

}
