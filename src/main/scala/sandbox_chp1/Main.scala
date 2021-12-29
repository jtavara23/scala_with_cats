package sandbox_chp1

import cats.instances.string._
import cats.syntax.semigroup._

object Main extends App {
  println("Hello " |+| "Cats!")
  /*
   *
  • traits: type classes;
  • implicit values: type class instances;
  • implicit parameters: type class use;
  • implicit classes: optional utilities that make type classes easier to use.
   * */

//  Summary
  /*
  * We saw the components that make up a type class:
    • A trait, which is the type class
    • Type class instances, which are implicit values.
    • Type class usage, which uses implicit parameters.
  * */

  /*
  The type classes themselves are generic traits in the cats package.
    • Each type class has a companion object with, an apply method for materializing instances, one or more construction methods for creating in‐
      stances, and a collection of other relevant helper methods.
    • Default instances are provided via objects in the cats.instances
      package, and are organized by parameter type rather than by type class.
    • Many type classes have syntax provided via the cats.syntax package.
  * */
  println(new TypeClassUseV2().result2)
}
