package functions_chp3

object FunctorSyntaxInCats extends App {

  import cats.instances.function._ // for Functor
  import cats.syntax.functor._ // for map and as
  val func1 = (a: Int) => a + 1
  val func2 = (a: Int) => a * 2
  val func3 = (a: Int) => s"${a}!"
  val func4 = func1.map(func2).map(func3)
  func4(123)
  // res3: String = "248!"


  import cats.Functor
  def doMath[F[_]](start: F[Int])(implicit functor: Functor[F]): F[Int] =
    start.map(n => n + 1 * 2)

  import cats.instances.option._ // for Functor
  import cats.instances.list._ // for Functor

  doMath(Option(20))
  // res4: Option[Int] = Some(22)

  doMath(List(1, 2, 3))
  // res5: List[Int] = List(3, 4, 5)




  final case class Box[A](value: A)
  val box = Box[Int](123)
  //  box.map(value => value + 1) // gives an error
  /*
    The map method of FunctorOps requires an implicit Functor as a parameter.
    This means this code will only compile if we have a Functor for F in scope.
  */

  implicit val BoxFunctor: Functor[Box] =
    new Functor[Box] {
      override def map[A, B](fa: Box[A])(func: A => B): Box[B] = Box(func(fa.value))
    }
  box.map(value => value + 1)
  // res6: Box[Int]  = Box(124)

  println(List(1,2,3).as("As"))
//  List(As, As, As)
}

object InstancesForCustomTypes extends App{

  import cats.Functor

  implicit val optionFunctor: Functor[Option] =
    new Functor[Option] {
      def map[A, B](value: Option[A])(func: A => B): Option[B] =
        value.map(func)
    }

  import scala.concurrent.{Future, ExecutionContext}

  implicit def futureFunctor(implicit ec: ExecutionContext): Functor[Future] =
    new Functor[Future] {
      def map[A, B](value: Future[A])(func: A => B): Future[B] =
        value.map(func)
    }
  implicit val executionContext = ExecutionContext.global

  // We write this:
  Functor[Future]
  // The compiler expands to this first:
  Functor[Future](futureFunctor)
  // And then to this:
  Functor[Future](futureFunctor(executionContext))
}
