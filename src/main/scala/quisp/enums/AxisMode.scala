package quisp.enums

/**
 * Created by rodneykinney on 4/28/15.
 */
object AxisMode {
  val CATEGORIES = categories

  case object categories extends AxisMode

}

sealed trait AxisMode extends EnumTrait