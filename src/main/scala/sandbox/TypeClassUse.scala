package sandbox

class TypeClassUseV1 {
  /*
   * --> 1.1.3) TYPE CLASS USE : implicit parameters
   * */

  /*
   * 1.1.3.1) INTERFACE OBJECT
       The simplest way of creating an interface that uses a type class is to place
       methods in a singleton object
   * */
  object MySingletonJson {
    def toJson[A](value: A)(implicit ser: JsonSerializer[A]): Json = ser.serialize(value)
  }

  import JsonSerializerInstances._ // this is NEEDED in order to use the implicit 'ser'

  val result: Json = MySingletonJson.toJson(Person("Josue", 34))
  //  JsObject(Map(NAME -> JsString(Josue), AGE -> JsInt(34)))
}

class TypeClassUseV2 {

  /*
   * 1.1.3.2) INTERFACE SYNTAX
       We can alternatively use extension methods to extend "existing types"
       with "interface methods". Cats refers to this as “syntax” for the type class.
   * */
  object MySyntaxJson {
    implicit class JsonSerializerOps[A](value: A) {
      def toJson(implicit ser: JsonSerializer[A]): Json =
        ser.serialize(value)
    }
  }

  import MySyntaxJson._ // this needs the serializer
  import JsonSerializerInstances._

  val result: Json = Person("Josue", 34).toJson
  //  JsObject(Map(NAME -> JsString(Josue), AGE -> JsInt(34)))

  val result2: Json = Option("Whatever").toJson
}
