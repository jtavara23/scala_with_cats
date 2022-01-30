package functions_chp3

object PartialUnification extends App {

  /* 3.8 Aside: Partial Unification
    In Section 3.2 we saw a functor instance for Function1.*/
  import cats.Functor
  import cats.instances.function._ // for Functor
  import cats.syntax.functor._ // for map
  val func1 = (x: Int) => x.toDouble
  val func2 = (y: Double) => y * 2
  val func3 = func1.map(func2)
  // func3: Int => Double = scala.Function1$$Lambda$7919/0

  /*Function1 has two type parameters
  (the function argument and the result type)
   */
  trait Function1[-A, +B] {
    def apply(arg: A): B
  }

  /*However, Functor accepts a type constructor with one parameter:*/
  trait Functor[F[_]] {
    def map[A, B](fa: F[A])(func: A => B): F[B]
  }
  /*
   * The compiler has to fix one of the two parameters of Function1 to create a
    type constructor of the correct kind to pass to Functor. It has two options to
    choose from:

  type F[A] = Int => A
  type F[A] = A => Double

   * */

  /*
  We know that the former of these is the correct choice. However the compiler
   doesn’t understand what the code means. Instead it relies on a simple rule,
    implementing what is called “partial unification”.

    The partial unification in the Scala compiler works by fixing type parameters
    from left to right. In the above example, the compiler fixes the Int in Int =>
  Double and looks for a Functor for functions of type Int => ?:
   *

  type F[A] = Int => A
  val functor = Functor[F]
   */

  /*This left‐to‐right elimination works for a wide variety of common scenarios,
  including Functors for types such as Function1 and Either:
   */
  val either: Either[String, Int] = Right(123)
  // either: Either[String, Int] = Right(123)

  val eitherMAP = either.map(_ + 1)
  // res0: Either[String, Int] = Right(124)

  /*Partial unification is the default behaviour in Scala 2.13. In earlier ver‐
  sions of Scala we need to add the -Ypartial-unification compiler
    flag. In sbt we would add the compiler flag in build.sbt:
    scalacOptions += "-Ypartial-unification"
   */

}
