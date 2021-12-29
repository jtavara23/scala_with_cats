package monoids_semigroups_chp2



object MonoidExercise extends App {

  import cats.Monoid
  import cats.syntax.semigroup._ // for |+|

  //--------------------------------------------------
  def add[A](items: List[A])(implicit monoid: Monoid[A] ): A =
    items.foldLeft(monoid.empty)(_ |+| _)
  //--------------------------------------------------
  //  We can optionally use Scala’s context bound syntax to write the same code in
  //   a shorter way:
  def addV2[A: Monoid](items: List[A]): A =
    items.foldLeft(Monoid[A].empty)(_ |+| _)
  //--------------------------------------------------

  import cats.instances.int._ // for Monoid
  add(List(1, 2, 3))
  // res10: Int = 6

  //--------------------------------------------------
//  import cats.instances.option._ // for Monoid
//  add(List(Some(1), Some(2), Some(3))) // this is wrong
  /*
    error: could not find implicit value for evidence parameter of type
      cats.Monoid[Some[Int]]
     add(List(Some(1), Some(2), Some(3)))
     ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     This happens because the inferred type of the list is List[Some[Int]],
     while Cats will only generate a Monoid for Option[Int].
   */
  //--------------------------------------------------

  case class Order(totalCost: Double, quantity: Double)
  implicit val monoid: Monoid[Order] = new Monoid[Order] {
    def combine(o1: Order, o2: Order) =
      Order(
        o1.totalCost + o2.totalCost,
        o1.quantity + o2.quantity
      )
    def empty = Order(0, 0)
  }

  add(List(Order(10,2), Order(3,3)))
  // res11:Order = Order(13.0,5.0)
}
