package functions_chp3


object ExerciseOfFunctors extends App {

  sealed trait Tree[+A]

  final case class Branch[A](left: Tree[A], right: Tree[A])
    extends Tree[A]

  final case class Leaf[A](value: A) extends Tree[A]

  import cats.Functor
  implicit val treeFunctor: Functor[Tree] =
    new Functor[Tree] {
      override def map[A, B](tree: Tree[A])(func: A => B): Tree[B] = {
        tree match {
          case Branch(l, r) => Branch(map(l)(func), map(r)(func))
          case Leaf(v) => Leaf(func(v))
        }
      }
    }

  // If we try to use fo the treeFunctor:
//  Branch(Leaf(10), Leaf(20)).map(_ * 2)

  // We will get the error: value map is not a member of repl.Session.App0.Branch[Int]
  // Branch(Leaf(10), Leaf(20)).map(_ * 2)


  // 'cause the compiler can find a Functor instance for Tree but not for Branch or Leaf

object Tree{
  def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
  def leaf[A](value: A): Tree[A] = Leaf(value)
}


  import cats.syntax.functor._ // for map
  // If we try to use fo the treeFunctor:
  println(Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2))

  println(Tree.leaf(100).map(_ * 2))
}
