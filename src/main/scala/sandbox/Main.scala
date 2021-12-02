package sandbox

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

  //  println(new TypeClassUseV2().result)
}
