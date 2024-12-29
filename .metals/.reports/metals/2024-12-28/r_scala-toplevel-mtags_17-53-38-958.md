error id: file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala:[148..149) in Input.VirtualFile("file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala", "// collection of words with letters over 5, random word generation 
// number of incorrect guesses = 5
// ASCII hangman art being drawn

object }

val words: Vector = Vector("banana, hena, maja")

object Main extends App {
  val x: Int = 1 + 1
  var y: Int = x + 3
  var block = {
    val c = 4
    c + " bruh"
  }

  val user1 = new User
  var point1 = new Point(1, 2, "Hena")

  val tup = ("Test", 1)
  println(tup._1)
}

class User

class Point(var x: Int, var y: Int, val name: String) {
  def readX(): Int = {
    return x
  }
  def readPoint(): Unit = println(s"($x, $y)")

  override def toString(): String = name
}
")
file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala:5: error: expected identifier; obtained rbrace
object }
       ^
#### Short summary: 

expected identifier; obtained rbrace