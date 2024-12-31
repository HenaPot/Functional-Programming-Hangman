error id: file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala:[2810..2813) in Input.VirtualFile("file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala", "import scala.util.Random
import scala.io.StdIn._
import ujson.Bool

val HangmanPics = Array(
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

  val user: User = initializeUser()
  val initialState = initializeGameState(user)

  playGame(initialState)
}

def playGame(state: GameState): Unit = {
  if (state.guessesLeft > 0 && state.pooledWordSet != state.wordSet) {

    drawHangman(state)
    println(generateKnownUnknownLetters(state.word, state.pooledWordSet))
    displayInformationMessages(state)

    var letter: Char = handleLetterInput()

    val (updatedState, hit) = updatedGameState(state, letter)

    playGame(updatedState)
  } else {
    //saves outcome in the json file as well in order to keep track of score
    if (state.pooledWordSet == state.wordSet) println("YOU WON CONGRATS") 
    else println("You silly failure") 
  }
}

def handleLetterInput(): Char = {
  print("Please enter a letter: ")
  try {
    val input = readChar().toLower
    if (input.isLetter) input
    else {
      println("Invalid input. Please enter a letter.")
      handleLetterInput()
    }
  }
  catch {
    case _: Exception => 
      println("Invalid input. Please try again.")
      handleLetterInput()
  }
}

def updatedGameState(state: GameState, letter: Char): (GameState, Boolean) = {
  val hit = state.word.contains(letter) && !state.pooledWordSet.contains(letter)
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

def 

def initializeUser(): User = {
  print("Please enter your username: ")
  try {
    val username = readLine()
    print("Please enter your password: ")
    val password = readLine()
    // add entry to json file
    User(username, password)
  }
  catch {
    case _: Exception => 
      println("Invalid input(s). Please try again.")
      initializeUser()
  }
} 

def initializeChoice(): Boolean = {
  print("Enter 'y' if you want to load your previous game, enter 'n' if you want to start a new game: ")
  try {
    val choice = readLine()

    choice match 
      case "y" => true
      case "n" => false
      case _ => initializeChoice()
  }
  catch {
    case _: Exception => 
      println("Invalid input. Please try again.")
      initializeChoice()
  }
}

def initializeGameState(choice: Boolean): GameState = {
  // either a new game is started or the previous game is loaded that is saved for this particular user
  //in case a new game is chosen
  if (choice)  {
    val newWord = Random.shuffle(words).head
    GameState(
        word = newWord,
        guessesLeft = HangmanPics.length - 1,
        hangmanState = 0,
        pooledWordSet = Set(),
        wordSet = newWord.toSet
      )
  } else {
    // resume the last game, read from a json file. 
  }
}


")
file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala:145: error: expected identifier; obtained def
def initializeUser(): User = {
^
#### Short summary: 

expected identifier; obtained def