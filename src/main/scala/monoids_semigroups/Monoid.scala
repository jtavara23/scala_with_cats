package monoids_semigroups


trait Monoid[A]{
  def combine(x:A, y:A) :A
  def empty:A
}

object Monoid {
  def associativeLaw[A](x: A, y: A, z: A)
                       (implicit m: Monoid[A]): Boolean = {
    m.combine(x, m.combine(y, z)) ==
      m.combine(m.combine(x, y), z)
  }
  def identityLaw[A](x: A)
                    (implicit m: Monoid[A]): Boolean = {
    (m.combine(x, m.empty) == x) &&
      (m.combine(m.empty, x) == x)
  }
}

/*
* Integer subtraction, for example, is not a monoid
* because subtraction is not associative
* */