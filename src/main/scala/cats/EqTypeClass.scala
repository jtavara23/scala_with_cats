package cats





final case class Cat(name:String, age: Int, color: String)

object EqTypeClass extends App {

  /*
    • === compares two objects for equality;
    • =!= compares two objects for inequality.
    Examples:
    123 === 123
      // res4: Boolean = true
    123 =!= 234
      // res5: Boolean = true
  * */

  /*
  * Unlike Scala’s == method, if we try to compare objects of different types using
    "eqv" we get a compile error:

    eqInt.eqv(123, "234")
        // error: type mismatch;
        // found : String("234")
  * */


  import cats.Eq
  import cats.syntax.eq._
  import cats.instances.int._
  import cats.instances.string._

  /*
  * We bring the Eq instances for Int and String into
  *  scope for the implementation of Eq[Cat]:
  * */
  implicit val catEqual: Eq[Cat] =
    Eq.instance[Cat] { (cat1, cat2) =>
      (cat1.name === cat2.name ) &&
        (cat1.age === cat2.age ) &&
        (cat1.color === cat2.color)
    }


  val cat1 = Cat("Garfield", 38, "orange and black")
  // cat1: Cat = Cat("Garfield", 38, "orange and black")
  val cat2 = Cat("Heathcliff", 32, "orange and black")
  // cat2: Cat = Cat("Heathcliff", 32, "orange and black")
  cat1 === cat2
  // res15: Boolean = false
  cat1 =!= cat2
  // res16: Boolean = true

  // same for Option values
  import cats.instances.option._
  val optionCat1 = Option(cat1)
  // optionCat1: Option[Cat] = Some(Cat("Garfield", 38, "orange and black"))
  val optionCat2 = Option.empty[Cat]
  // optionCat2: Option[Cat] = None
  optionCat1 === optionCat2
  // res17: Boolean = false
  optionCat1 =!= optionCat2
  // res18: Boolean = true
}
