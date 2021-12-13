package cats

import cats.Show
import cats.instances.int._ // for Show
import cats.instances.string._ // for Show
import cats.syntax.show._ // for show

object PrintableCats extends App {

  final case class Cat(name: String, age: Int, color: String)

  implicit val catShow: Show[Cat] = { cat =>
    val name = cat.name.show
    val age = cat.age.show
    val color = cat.color.show
    s"$name is a $age year-old $color cat."
  }

  val cat = Cat("Lola", 11, "gray brown")

  println(cat.show)
}
