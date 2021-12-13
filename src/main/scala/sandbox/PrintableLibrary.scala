package sandbox

/*
* ============================
Printable Library
* ============================
 */

/*
* 1. Define a type class Printable[A] containing a single method format.
format should accept a value of type A and return a String.
* */
trait Printable[A] {
  def format(value:A): String
}

/*
2. Create an object PrintableInstances containing instances of
Printable for String and Int.
*/
object PrintableInstance {
  implicit val stringPrintable: Printable[String] = new Printable[String] {
    override def format(value: String): String = value
  }

  implicit val intPrintable: Printable[Int] = new Printable[Int] {
    override def format(value: Int): String = value.toString
  }
}

/*
* 3. Define an object Printable with two generic interface methods:
 - format accepts a value of type A and a Printable of the correspond‐
ing type. It uses the relevant Printable to convert the A to a String.
 - print accepts the same parameters as format and returns Unit. It
prints the formatted A value to the console using println.
* */

object Printable{
  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)
  def print[A](value: A)(implicit p: Printable[A]): Unit = println(format(value))
}

/*
* ============================
 Usage of Printable Library
* ============================
 */

object PrintableLibrary extends App{
  final case class Cat(name: String, age: Int, color: String)
  val cat = Cat("Lola", 11, "gray brown")

  //  5) Implementation
  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat): String = {
      import PrintableInstance._
      val name = Printable.format(value.name)
      val age = Printable.format(value.age)
      val color = Printable.format(value.color)
      s"$name is a $age year-old $color cat."
    }
  }


//  4) this is the expected used of "Printable" library
  Printable.print(cat)





}