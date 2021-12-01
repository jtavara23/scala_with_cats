package sandbox

sealed trait Json {
  final case class JsObject(value: Map[String, Json])
  final case class JsString(value: String)
  final case class JsInt(value: Int)
  final case object JsNull
}

trait
