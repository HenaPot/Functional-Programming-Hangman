file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala
### java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/Hena/AppData/Local/Coursier/cache/arc/https/github.com/adoptium/temurin11-binaries/releases/download/jdk-11.0.25%25252B9/OpenJDK11U-jdk_x64_windows_hotspot_11.0.25_9.zip/jdk-11.0.25+9/lib/src.zip!/java.base/java/lang/String.java

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 3375
uri: file:///C:/Faks%203/PL/hangman/src/main/scala/Main.scala
text:
```scala
import scala.util.Random
import scala.io.StdIn._
import ujson.Bool

val HangmanPics = Array(
  """
  +---+





=========
  """,
  """
  +---+

  O   |



=========
  """,
  """
  +---+

  O   |



=========
  """,
  """
  +---+

  O   |
 /|   |


=========
  """,
  """
  +---+

  O   |
 /|\  |


=========
  """,
  """
  +---+

  O   |
 /|\  |
 /    |

=========
  """,
  """
  +---+

  O   |
 /|\  |
 / \  |

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
    choice m@@
  }
  catch {
    case _: Exception => 
      println("Invalid input(s). Please try again.")
      initializeChoice()
  }
}

def initializeGameState(choice: Boolean): GameState = {
  // either a new game is started or the previous game is loaded that is saved for this particular user
  //in case a new game is chosen

  if (newGame)  {
    val newWord = Random.shuffle(words).head
    GameState(
        user = user,
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



```



#### Error stacktrace:

```
java.base/sun.nio.fs.WindowsPathParser.normalize(WindowsPathParser.java:182)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:153)
	java.base/sun.nio.fs.WindowsPathParser.parse(WindowsPathParser.java:77)
	java.base/sun.nio.fs.WindowsPath.parse(WindowsPath.java:92)
	java.base/sun.nio.fs.WindowsFileSystem.getPath(WindowsFileSystem.java:232)
	java.base/java.nio.file.Path.of(Path.java:147)
	java.base/java.nio.file.Paths.get(Paths.java:69)
	scala.meta.io.AbsolutePath$.apply(AbsolutePath.scala:58)
	scala.meta.internal.metals.MetalsSymbolSearch.$anonfun$definitionSourceToplevels$2(MetalsSymbolSearch.scala:70)
	scala.Option.map(Option.scala:242)
	scala.meta.internal.metals.MetalsSymbolSearch.definitionSourceToplevels(MetalsSymbolSearch.scala:69)
	dotty.tools.pc.completions.CaseKeywordCompletion$.dotty$tools$pc$completions$CaseKeywordCompletion$$$sortSubclasses(MatchCaseCompletions.scala:342)
	dotty.tools.pc.completions.CaseKeywordCompletion$.matchContribute(MatchCaseCompletions.scala:292)
	dotty.tools.pc.completions.Completions.advancedCompletions(Completions.scala:350)
	dotty.tools.pc.completions.Completions.completions(Completions.scala:120)
	dotty.tools.pc.completions.CompletionProvider.completions(CompletionProvider.scala:90)
	dotty.tools.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:146)
```
#### Short summary: 

java.nio.file.InvalidPathException: Illegal char <:> at index 3: jar:file:///C:/Users/Hena/AppData/Local/Coursier/cache/arc/https/github.com/adoptium/temurin11-binaries/releases/download/jdk-11.0.25%25252B9/OpenJDK11U-jdk_x64_windows_hotspot_11.0.25_9.zip/jdk-11.0.25+9/lib/src.zip!/java.base/java/lang/String.java