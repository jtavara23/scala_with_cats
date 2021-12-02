package sandbox

//Abstract Type
sealed trait Json

final case class JsObject(value: Map[String, Json]) extends Json
final case class JsString(value: String) extends Json
final case class JsInt(value: Int) extends Json
case object JsNull extends Json

final case class Person(name: String, age: Int)

//---------------------------------------------------

/*
 *  --> 1.1.1) TYPE CLASS
 * */
trait JsonSerializer[A] {
  def serialize(value: A): Json
}



object JsonSerializerInstances {
  /*
   * --> 1.1.2) TYPE CLASS INSTANCE : implicit value creation
   * */
  implicit val intSerializer: JsonSerializer[Int] = new JsonSerializer[Int] {
    def serialize(value: Int): Json = JsInt(value)
  }

  implicit val stringSerializer: JsonSerializer[String] =
    (value: String) => JsString(value)

  implicit val personSerializer: JsonSerializer[Person] = (value: Person) =>
    JsObject(
      Map(
        "NAME" -> JsString(value.name),
        "AGE" -> JsInt(value.age),
      )
    )
}
