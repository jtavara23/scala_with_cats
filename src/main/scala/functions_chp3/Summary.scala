package functions_chp3

class Summary {

  /*Functors represent sequencing behaviours. We covered three types of functor
in this chapter:

  • Regular covariant Functors, with their map method, represent the abil‐
  ity to apply functions to a value in some context. Successive calls to
  map apply these functions in sequence, each accepting the result of its
  predecessor as a parameter.
  • Contravariant functors, with their contramap method, represent the
  ability to “prepend” functions to a function‐like context. Successive
  calls to contramap sequence these functions in the opposite order to
  map.
  • Invariant functors, with their imap method, represent bidirectional
  transformations.

  Regular Functors are by far the most common of these type classes, but even
  then it is rare to use them on their own. Functors form a foundational building
  block of several more interesting abstractions that we use all the time. In
  the following chapters we will look at two of these abstractions: monads and
  applicative functors.

  Functors for collections are extremely important, as they transform each el‐
  ement independently of the rest. This allows us to parallelise or distribute
  transformations on large collections, a technique leveraged heavily in “map‐
  reduce” frameworks like Hadoop. We will investigate this approach in more
  detail in the map‐reduce case study later in Section 9.
   */
}
