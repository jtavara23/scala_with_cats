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

/*
 * 1.2P TYPE CLASS INSTANCES can be package in 4 ways:
 * 1. by placing them in an object such as "JsonSerializerInstances";
 *   ** we bring instances into scope by importing them
 * 2. by placing them in a trait;
 *   ** we bring instances into scope with inheritance
 * 3. by placing them in the companion object of the type class;
 *   ** are always in implicit scope (conventional way)
 * 4. by placing them in the companion object of the parameter type.
 *   ** are always in implicit scope (conventional way)
 * */

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

  /*
   * 1.2) Recursive Implicit Resolution
   */
  /*
   * Earlier we insinuated that all type class instances are implicit vals.
   This was a simplification. We can actually define instances in two ways:
  1. by defining concrete instances as implicit vals of the required type;
   *  (We can also use an implicit object,
   *   which provides the same thing as an implicit val)
  2. by defining implicit methods to construct instances from
     other type class instances.
   * */

  implicit def optionSerializer[A](implicit ser: JsonSerializer[A]): JsonSerializer[Option[A]] =
    new JsonSerializer[Option[A]] {
      def serialize(value: Option[A]): Json = value match {
        case Some(aVal) => ser.serialize(aVal)
        case None => JsNull
      }
    }
}
