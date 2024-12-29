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

val words = Array("banana", "test", "osmoza", "hena", "maja")

@main def hangman(): Unit = {
  println("Welcome to the game of hangman!")

  val word = Random.shuffle(words).head

  val initialState = GameState(
    guessesLeft = HangmanPics.length - 1,
    hangmanState = 0,
    pooledWordSet = Set(),
    wordSet = word.toSet
  )

  playGame(initialState, word)
}

def playGame(state: GameState, word: String): Unit = {
  if (state.guessesLeft > 0 && state.pooledWordSet != state.wordSet) {

    drawHangman(state)
    println(generateKnownUnknownLetters(word, state.pooledWordSet))
    displayInformationMessages(state)

    var letter: Char = handleIOErrors()

    val (updatedState, hit) = updatedGameState(state, word, letter)

    playGame(updatedState, word)
  } else {
    if (state.pooledWordSet == state.wordSet) println("YOU WON CONGRATS")
    else println("You silly failure")
  }
}

def handleIOErrors(): Char = {
  print("Please enter a letter: ")
  try {
    val input = readChar().toLower
    if (input.isLetter) input
    else {
      println("Invalid input. Please enter a letter.")
      handleIOErrors()
    }
  }
  catch {
    case _: Exception => 
      println("Invalid input. Please try again.")
      handleIOErrors()
  }
}

def updatedGameState(state: GameState, word: String, letter: Char): (GameState, Boolean) = {
  val hit = word.contains(letter) && !state.pooledWordSet.contains(letter)
  val newPooledWordSet = if (hit) state.pooledWordSet + letter else state.pooledWordSet
  val newState = state.copy(
    guessesLeft = if (hit) state.guessesLeft else state.guessesLeft - 1,
    hangmanState = if (hit) state.hangmanState else state.hangmanState + 1,
    pooledWordSet = newPooledWordSet
  )
  (newState, hit)
}

def drawHangman(state: GameState): Unit = {
  println(HangmanPics(state.hangmanState))
}

def generateKnownUnknownLetters(word: String, pooledWordSet: Set[Char]): String = {
  word.map {
    c => if (pooledWordSet.contains(c)) c else '_'
  }.mkString(" ")
}

def displayInformationMessages(state: GameState): Unit = {
  println("Guesses remaining: " + state.guessesLeft)
}


