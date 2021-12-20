package monoids_semigroups

object MonoidsInCats extends App {

  import cats.Monoid
  import cats.instances.string._ // for Monoid
  Monoid[String].combine("Hi ", "there")
  // res0: String = "Hi there"
  Monoid[String].empty
  // res1: String = ""

  /*
  * which is equivalent to
  * */

  Monoid.apply[String].combine("Hi ", "there")
  // res2: String = "Hi there"
  Monoid.apply[String].empty

  /*
  * As we know, Monoid extends Semigroup. If we don’t need empty we can
  equivalently write:
  * */

  import cats.Semigroup
  Semigroup[String].combine("Hi ", "there")
  // res4: String = "Hi there"


  /*The type class instances for Monoid are organised under cats.instances in
    the standard way described in Chapter 1.4.2. For example, if we want to pull
    in instances for Int we import from cats.instances.int:*/

  import cats.Monoid
  import cats.instances.int._ // for Monoid
  Monoid[Int].combine(32, 10)
  // res5: Int = 42

  import cats.instances.option._ // for Monoid
  val a = Option(22)
  // a: Option[Int] = Some(22)
  val b = Option(20)
  // b: Option[Int] = Some(20)
  Monoid[Option[Int]].combine(a, b)


/*
* As always, unless we have a good reason to import individual instances, we
can just import everything.
    import cats._
    import cats.implicits._
* */

}
