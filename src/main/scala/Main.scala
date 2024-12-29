// collection of words with letters over 5, random word generation 
// number of incorrect guesses = 6
// ASCII hangman art being drawn
import scala.util.Random
import scala.io.StdIn.readChar

val HangmanPics: Array[String] = Array(
  """
  +---+
  |   |
      |
      |
      |
      |
=========
  """,
  """
  +---+
  |   |
  O   |
      |
      |
      |
=========
  """,
  """
  +---+
  |   |
  O   |
  |   |
      |
      |
=========
  """,
  """
  +---+
  |   |
  O   |
 /|   |
      |
      |
=========
  """,
  """
  +---+
  |   |
  O   |
 /|\  |
      |
      |
=========
  """,
  """
  +---+
  |   |
  O   |
 /|\  |
 /    |
      |
=========
  """,
  """
  +---+
  |   |
  O   |
 /|\  |
 / \  |
      |
=========
  """
)

val words: Vector[String] = Vector("banana", "test", "osmoza", "hena", "maja")
val word: String = words(Random.nextInt(words.length))

@main def hangman(): Unit = {
  var guessesLeft = HangmanPics.length - 1 //6
  var hangmanState = 0
  var gameEnd = false
  var pooledWordSet: Set[Char] = Set()
  var wordSet: Set[Char] = word.toSet

  println(word)
  for character <- word do print("_ ")
  print(HangmanPics(0))

  while(guessesLeft > 0 && !gameEnd) {
    print("Please enter a letter: ")
    var hit = false

    var letter: Char = handleIOErrors()

    for (character <- word) {
      if (character == letter && !pooledWordSet.contains(letter)) then
        hit = true
        print(s"$letter ")
        pooledWordSet = pooledWordSet + character
      else if (pooledWordSet.contains(character)) then
        print(s"$character ")
      else 
        print("_ ")
    }
   


    if !hit then 
      guessesLeft -= 1
      hangmanState += 1
    else
      println()
      println("Good job!")

    print(HangmanPics(hangmanState))
    println("Guesses remaining: " + guessesLeft)

    gameEnd = pooledWordSet == wordSet
  }

  if gameEnd then 
    println("YOU WON CONGRATS")
  else 
    println("You silly failure")
}

def handleIOErrors(): Char = 
  try 
    readChar().toLower
  catch
    case e: Exception => ' '



