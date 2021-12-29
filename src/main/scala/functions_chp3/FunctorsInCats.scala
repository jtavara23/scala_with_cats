package functions_chp3

object FunctorsInCats {

  import cats.Functor
  import cats.instances.list._ // for Functor
  import cats.instances.option._ // for Functor

  val list1 = List(1, 2, 3)
  // list1: List[Int] = List(1, 2, 3)
  val list2 = Functor[List].map(list1)(_ * 2)
  // list2: List[Int] = List(2, 4, 6)

  val option1 = Option(123)
  // option1: Option[Int] = Some(123)
  val option2 = Functor[Option].map(option1)(_.toString)
  // option2: Option[String] = Some("123")

  /*
  * Functor provides a method called lift, which converts a function
  *  of type A=> B to one that operates over a functor
  *  and has type F[A] => F[B]:
*/
  val func = (x: Int) => x + 1
  // func: Int => Int = <function1>
  val liftedFunc = Functor[Option].lift(func)
  // liftedFunc: Option[Int] => Option[Int] = cats.Functor$$Lambda$7972

  liftedFunc(Option(1))
  // res1: Option[Int] = Some(2)


  /*
  The as method is the other method you are likely to use.
   It replaces with value inside the Functor with the given value.*/
    Functor[List].as(list1, "As")
  // res2: List[String] = List("As", "As", "As")


}
