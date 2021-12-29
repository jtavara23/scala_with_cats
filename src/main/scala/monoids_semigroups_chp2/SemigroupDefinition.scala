package monoids_semigroups_chp2

object SemigroupDefinition {

  /*
  * A semigroup is just the combine part of a monoid, without the empty part.
  While many semigroups are also monoids, there are some data types for which
  we cannot define an empty element. For example, we have just seen that
  sequence concatenation and integer addition are monoids. However, if we
  restrict ourselves to non‐empty sequences and positive integers, we are no
  longer able to define a sensible empty element. Cats has a NonEmptyList
  data type that has an implementation of Semigroup but no implementation
  of Monoid.
  *
  * */

//  A more accurate (though still simplified) definition of Cats’ Monoid is:
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }
  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }


}

