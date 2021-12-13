package cats


object CatsShow extends App{

  /*
  * 1.4.2 Importing Default Instances
  * */
  import cats.instances.int._ // for Show
  import cats.instances.string._ // for Show
  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]

  val intAsString= showInt.show(213)
  val stringAsString= showString.show("213")


  /*
  * 1.4.3 Importing Interface Syntax
  * */
  import cats.syntax.show._ // for show
  val shownInt = 123.show
  // shownInt: String = "123"
  val shownString = "abc".show
  // shownString: String = "abc"

}
