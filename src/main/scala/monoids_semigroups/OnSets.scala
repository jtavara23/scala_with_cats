package monoids_semigroups

/*
* The monoid type class is cats.kernel.Monoid, which is aliased as
cats.Monoid. Monoid extends cats.kernel.Semigroup, which is aliased
as cats.Semigroup.
* */

import cats.{Monoid, Semigroup}

object OnSets1 extends App {

  /*
  * We need to define setUnionMonoid as a method rather than a value so we
    can accept the type parameter "A". The type parameter allows us to use the
    same definition to summon Monoids for Sets of any type of data:
  * */
  implicit def setUnionMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] = a union b

      def empty = Set.empty[A]
    }


  val intSetMonoid = Monoid[Set[Int]]
  val strSetMonoid = Monoid[Set[String]]
  intSetMonoid.combine(Set(1, 2), Set(2, 3))
  // res18: Set[Int] = Set(1, 2, 3)
  strSetMonoid.combine(Set("A", "B"), Set("B", "C"))
  // res19: Set[String] = Set("A", "B", "C")

}
object OnSets2 extends App{
  /*
  * Set intersection forms a semigroup,
  *  but doesn’t form a monoid because it has no identity element:
  * */

  implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] =
    new Semigroup[Set[A]] {
      def combine(a: Set[A], b: Set[A]) =
        a intersect b
    }


/*
* Set complement and set difference are not associative, so they cannot be considered for either monoids or semigroups. However, symmetric difference
(the union less the intersection) does also form a monoid with the empty set:
* */

  implicit def symDiffMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] =
        (a diff b) union (b diff a)
      def empty: Set[A] = Set.empty
    }
}
