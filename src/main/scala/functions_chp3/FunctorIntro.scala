package functions_chp3


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}

object FunctorIntro extends App {
/*
   Informally a FUNCTOR is anything with a map method, like Option, List, Either
   We specify the function to apply, and map ensures it is applied to
   every item. The values change but the structure of the list (the number of
   elements and their order) remains the same
   *
   Similarly, when we map over an Option, we transform the contents but leave
   the Some or None context unchanged. The same principle applies to Either
   with its Left and Right contexts
   *
   We should think of map not as an iteration pattern, but as a way of sequencing
    computations on values and this computations are functions applied eagerly
* */

  val futureFunctor = Future(123)
  /*
  * When we work with a Future we have no guarantees about its internal state.
  The wrapped computation may be ongoing, complete, or rejected.
  * If the Future is complete, our mapping function can be called immediately.
  * If not, some underlying thread pool queues the function call and comes back to it
  later. We don’t know when our functions will be called, but we do know what
  order they will be called in. In this way, Future provides the same sequencing
  behaviour seen in List, Option, and Either:
  *
  * */
  val future: Future[String] =
    futureFunctor.
      map(n => n + 1).
      map(n => n * 2).
      map(n => s"${n}!")
  import scala.concurrent.duration._
  Await.result(future, 1.second)
  // res2: String = "248!"

  /*
  * REFERENTIAL TRANSPARENCY
  * Note that Scala’s Futures aren’t a great example of pure functional programming
    because they aren’t referentially transparent.
  * Future always computes and caches a result and
    there’s no way for us to tweak this behaviour.
    This means we can get unpredictable results when we use Future to wrap side‐effecting computations.
  * */

  import scala.util.Random

  val future11 = {
    // Initialize Random with a fixed seed:
    val r = new Random(0L)
    // nextInt has the side-effect of moving to
    // the next random number in the sequence:
    val x = Future(r.nextInt)
    for {
      a <- x // calls future (with nextInt) once
      b <- x // DOES NOT call future (with nextInt) once again
    } yield (a, b)
  }

  val future22 = {
    val r = new Random(0L)
    for {
      a <- Future(r.nextInt)
      b <- Future(r.nextInt)
    } yield (a, b)
  }
  val result1 = Await.result(future11, 1.second)
  // result1: (Int, Int) = (-1155484576, -1155484576)
  val result2 = Await.result(future22, 1.second)
  // result2: (Int, Int) = (-1155484576, -723955400)

  /*However this works fine with "Task". Note that the interesting one is the non-inlined version,
   which manages to produce two distinct Int values.
   This is the important bit: Task has a constructor that captures side-effects as values,
   and Future does not.*/

  import scalaz.concurrent.Task
  val task1 = {
    val r = new Random(0L)
    val x = Task.delay(r.nextInt)
    for {
      a <- x
      b <- x
    } yield (a, b)
  }

  // Same as task1, but I inlined `x`
  val task2 = {
    val r = new Random(0L)
    for {
      a <- Task.delay(r.nextInt)
      b <- Task.delay(r.nextInt)
    } yield (a, b)
  }

  task1.unsafePerformAsync(println) // (-1155484576,-723955400)
  task2.unsafePerformAsync(println) // (-1155484576,-723955400)
  /*
    Most of the commonly-cited differences like "a Task doesn't run until you ask it to" and
    "you can compose the same Task over and over" trace back to this fundamental distinction.
    So the reason it's "totally unusable" is that once you're used to programming with pure values
    and relying on equational reasoning to understand and manipulate programs it's hard to go back to side-effecty world
     where things are much harder to understand.
  */

  /*
  * Another problem with FUTUREs is it always starts computations
    immediately rather than allowing the user to dictate when the program
    should run.
    *
    * When we look at Cats Effect we’ll see that the IO type solves these
    problems.
  * */
}
