package functions_chp3

object ContraVariantFunctors extends App {

//  the contravariant functor, provides an operation
  // called contramap that represents “prepending” an operation to a chain.

  /*
   * The contramap method only makes sense for data types that represent transformations.
   *  For example, we can’t define contramap for an Option because
there is no way of feeding a value in an Option[B] backwards through a function A => B
   * */

  // Define the Trait
  trait Printable[A] { self =>
    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] =
      new Printable[B] {
        def format(value: B): String =
          self.format(func(value))
      }
  }

  /*
    Define the "format" function
   */

  def format[A](value: A)(implicit p: Printable[A]): String =
    p.format(value)

  /*Implementation of tests for the "format" function*/
  implicit val intPrintable: Printable[Int] =
    new Printable[Int] {
      def format(value: Int): String =
        s"'${value}'"
    }
  implicit val booleanPrintable: Printable[Boolean] =
    new Printable[Boolean] {
      def format(value: Boolean): String =
        if (value) "yes" else "no"
    }
  println(format(55))
  // res2: String = "'55'"
  println(format(true))
  // res3: String = "yes"

  /*Definition of an instance Printable for Box case class*/

  final case class Box[A](value: A)
  /*
   * Now
   * Rather than writing out the complete definition from scratch (new Printable[Box] etc…),
   *  create your instance from an existing instance using contramap.
   *
   * */

//  format(Box(2))
  // -- no implicits found for parameter p: Printable[Box[Int]
  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
    new Printable[Box[A]] {
      def format(box: Box[A]): String =
        p.format(box.value)
    }

  println(format(Box(2)))
  // res4: String = "'2'"
  println(format(Box(false)))
  // res7: String = "no"

  implicit def boxPrintable[A](implicit p: Printable[A]): Printable[Box[A]] =
    p.contramap[Box[A]](x => x.value)

  println(format(Box(2)))
  // res6: String = "'2'"
  println(format(Box(true)))
  // res7: String = "yes"
}
