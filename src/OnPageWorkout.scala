import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.*
import scala.scalajs.js.JSConverters.*

import org.scalajs.dom.*

import Functional.*
import Garmin.*
import GetElement.*
import GetMutationRecord.*
import GetURL.*
import Regex.*
import Unapply.*

object OnPageWorkout:
  def apply()(using a: Anchor[HTMLElement], s: Scatter[ActivityLaps], b: Box[ActivityLaps]): Route =
    case (Workout(Seq(_, id: String), "running"), PageHeader(e)) =>
      for list <- activityLapsList(id.toDouble, 7) do
        Plot(a.init(e.appendChild, "box"), list, "近七日速心比分布趋势")(using _.box)
        Plot(a.init(e.appendChild, "scatter"), list, "近七日速心比趋势对比")(using _.scatter)

  private val Workout = Extract[URL, (Seq[UndefOr[String]], UndefOr[String])]:
    (path |> "/modern/workout/(\\d+)".capture) ~> param("workoutType")

  private val PageHeader = Extract[Seq[MutationRecord], Element]:
    inline def E = Extract[Element, Element]:
      query("div[class*=\"PageHeader\"]").??

    _.flatMap(addedNodes[Element]).collectFirst({ case E(e) => e })

end OnPageWorkout

object GetMutationRecord:
  import scala.reflect.Typeable
  inline def addedNodes[A >: Element: Typeable]: MutationRecord => Seq[A] =
    _.addedNodes.toSeq.collect({ case a: A => a })

object GetElement:
  inline def query(selector: String): Element => Element = _.querySelector(selector)
