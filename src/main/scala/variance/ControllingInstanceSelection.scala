package variance

import sandbox_chp1.{JsInt, JsObject, JsString}


object ControllingInstanceSelection extends App {



  /*
  * -- COVARIANCE:
  *    trait F[+A] // the "+" means "covariant"
  *
  * allows us to substitute collections of one type with a collection
  * of a subtype data that we can later get out of a container type
  * such as List, or otherwise returned by some method. (READ OPERATIONS)
  * */

  sealed trait Shape
  case class Circle(radius: Double) extends Shape
  val circles: List[Circle] = List(Circle(2.3))
  val shapes: List[Shape] = circles



  /*
  * -- Contravariance:
  *    trait F[-A] // the "-" means "Contravariance"
  *
  * allows us modelling types that represent inputs
  * */

  import sandbox_chp1.JsonSerializer
  import sandbox_chp1.Json

  val shape: Shape = Circle(3.3)
  val circle: Circle = Circle(2.4)
  val shapeWriter: JsonSerializer[Shape] = (value: Shape) => JsString(value.toString)
  val circleWriter: JsonSerializer[Circle] = (value: Circle) => JsObject(
    Map("radio" -> JsInt(value.radius.toInt))
  )
  def format[A](value: A, serializer: JsonSerializer[A]): Json = {
    serializer.serialize(value)
  }
  //This means we can use "shapeWriter"
  //anywhere we expect to see a "JsonWriter[Circle]"
  println(format(circle, shapeWriter))
  println(format(circle, circleWriter))


  /*
  * Type Class Variance        | Invariant | Covariant | Contravariant
    Supertype instance used?    |  No      |No         |     Yes
    More specific type preferred? | No     | Yes        | No
  * */
}

