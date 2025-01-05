import scala.util.Random
import scala.io.StdIn.readLine
import upickle.default.{read, write}
import os._

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

val words = Array(
  "banana", "elephant", "chicken", "purple", "amazing", "mountain", "journey", "holiday", "freedom", "rainbow",
  "diamond", "fiction", "picture", "thought", "windows", "sunshine", "welcome", "science", "history", "dolphin",
  "octopus", "pyramid", "library", "unicorn", "blossom", "comfort", "forever", "giraffe", "imagine", "luggage",
  "orchard", "painter", "shelter", "teacher", "vaccine", "whistle", "concert", "deserve", "example", "grateful",
  "justice", "kingdom", "laughter", "morning", "natural", "outside", "pioneer", "quality", "respect", "seaside",
  "texture", "umbrella", "village", "whisper", "zipper", "beehive", "capture", "curtain", "delight", "eclipse",
  "fantasy", "genuine", "harvest", "inspire", "journey", "kinetic", "loyalty", "mission", "novelty", "orchard",
  "passion", "quality", "rescue", "sunrise", "trouble", "upgrade", "victory", "wisdom", "yellow", "zealous",
  "builder", "chimney", "dessert", "enchant", "fortune", "glimmer", "harvest", "iceberg", "justice", "kingdom",
  "leisure", "miracle", "network", "outlook", "pattern", "respect", "student", "treasure", "venture", "weather"
)
val dataFile = os.pwd / "hangman_data.json"
val multiplayerDataFile = os.pwd / "hangman_multiplayer_data.json"

@main def hangman(): Unit = {
  println("Welcome to Hangman!")
  val users = loadUsers()

  val username = prompt("Enter your username:")
  val password = prompt("Enter your password:")

  val user = users.get(username) match {
    case Some(user) if user.password == password => user
    case Some(_) =>
      println("Incorrect password."); return
    case None =>
      println("New user detected. Registering...")
      val newUser = UserData(username, password, Nil)
      saveUsers(users + (username -> newUser))
      newUser
  }

  mainMenu(user, users)
}

def mainMenu(user: UserData, users: Map[String, UserData]): Unit = {
  println("\nMain Menu:")
  println("1. Start a new game")
  println("2. Resume last game")
  println("3. View scoreboard")
  println("4. Multiplayer mode (set a word for another player)")
  println("5. Multiplayer mode (play given words)")
  println("6. Quit")

  readLine("Choose an option: ") match {
    case "1" => startNewGame(user, users)
    case "2" => resumeGame(user, users)
    case "3" => viewScoreboard(users)
    case "4" => multiplayerMode(user, users)
    case "5" => playMultiplayerGame(user, users)
    case "6" => println("Goodbye!")
    case _ =>
      println("Invalid option. Try again.")
      mainMenu(user, users)
  }
}

def startNewGame(user: UserData, users: Map[String, UserData]): Unit = {
  val word = Random.shuffle(words).head
  val initialState = GameState(
    word = word,
    guessesLeft = HangmanPics.length - 1,
    hangmanState = 0,
    pooledWordSet = Set(),
    wordSet = word.toSet
  )
  playGame(initialState, user, users)
}

def resumeGame(user: UserData, users: Map[String, UserData]): Unit = {
  user.games.lastOption match {
    case Some(game) => playGame(game, user, users)
    case None =>
      println("No saved games found.")
      mainMenu(user, users)
  }
}

def viewScoreboard(users: Map[String, UserData]): Unit = {
  println("\nScoreboard:")

  if (users.isEmpty) {
    println("No users available to display.")
  } else {
    users.foreach { case (username, data) =>
      val totalGames = data.games.size
      val wins = data.games.count(gameWon)
      println(s"$username: $wins wins out of $totalGames games")
    }
  }
  if (users.nonEmpty) {
    mainMenu(users.head._2, users)
  } else {
    println("Returning to the game start.")
    hangman()
  }
}

def multiplayerMode(currentUser: UserData, users: Map[String, UserData]): Unit = {
  println("\nMultiplayer Mode:")
  if (users.size < 2) {
    println("Not enough users for multiplayer mode.")
    mainMenu(currentUser, users)
    return
  }

  val otherUsers = users.keys.filter(_ != currentUser.username).toList
  println("Available users to set a word for:")
  otherUsers.zipWithIndex.foreach { case (username, index) =>
    println(s"${index + 1}. $username")
  }

  val selectedIndex = readLine("Choose a user by number: ").toIntOption
  selectedIndex match {
    case Some(index) if index > 0 && index <= otherUsers.size =>
      val chosenUser = otherUsers(index - 1)
      val word = prompt(s"Enter a word for $chosenUser:").toLowerCase
      if (word.forall(_.isLetter)) {
        val multiplayerData = loadMultiplayerData()
        val updatedData = multiplayerData.updatedWith(chosenUser) {
          case Some(words) => Some(words :+ word)
          case None        => Some(List(word))
        }
        saveMultiplayerData(updatedData)
        println(s"The word has been set for $chosenUser!")
        mainMenu(currentUser, users)
      } else {
        println("Invalid word. Only letters are allowed.")
        multiplayerMode(currentUser, users)
    }
    case _ =>
      println("Invalid selection. Try again.")
      multiplayerMode(currentUser, users)
  }
}

def playMultiplayerGame(user: UserData, users: Map[String, UserData]): Unit = {
  val multiplayerData = loadMultiplayerData()
  multiplayerData.get(user.username) match {
    case Some(words) if words.nonEmpty =>
      val word = words.head
      val initialState = GameState(
        word = word,
        guessesLeft = HangmanPics.length - 1,
        hangmanState = 0,
        pooledWordSet = Set(),
        wordSet = word.toSet
      )
      val updatedData = multiplayerData.updated(user.username, words.tail)
      saveMultiplayerData(updatedData)
      playGame(initialState, user, users)
    case _ =>
      println("No multiplayer games available.")
      mainMenu(user, users)
  }
}

def playGame(state: GameState, user: UserData, users: Map[String, UserData]): Unit = {
  if (state.guessesLeft > 0 && state.pooledWordSet != state.wordSet) {
    drawHangman(state)
    println(generateKnownUnknownLetters(state.word, state.pooledWordSet))
    println(s"Guesses remaining: ${state.guessesLeft}")

    val input = prompt("Enter a letter (or type 'save' to save the game):").toLowerCase

    if (input == "save") {
      val updatedUsers = saveGame(user, state, users)
      println("Game saved!")
      mainMenu(user.copy(games = user.games :+ state), updatedUsers)
    } else if (input.length == 1 && input.head.isLetter) {
      val letter = input.head
      val hit = state.word.contains(letter)
      val newState = state.copy(
        guessesLeft = if (hit) state.guessesLeft else state.guessesLeft - 1,
        hangmanState = if (hit) state.hangmanState else state.hangmanState + 1,
        pooledWordSet = if (hit) state.pooledWordSet + letter else state.pooledWordSet
      )
      playGame(newState, user, users)
    } else {
      println("Invalid input. Please try again.")
      playGame(state, user, users)
    }
  } else {
    if (gameWon(state)) println("Congratulations, you won!")
    else println(s"Game over! The word was: ${state.word}")

    val updatedUsers = saveGame(user, state, users)
    mainMenu(user, updatedUsers)
  }
}

def gameWon(state: GameState): Boolean = state.pooledWordSet == state.wordSet

def drawHangman(state: GameState): Unit = println(HangmanPics(state.hangmanState))

def generateKnownUnknownLetters(word: String, pooledWordSet: Set[Char]): String =
  word.map(c => if (pooledWordSet.contains(c)) c else '_').mkString(" ")

def loadUsers(): Map[String, UserData] = {
  if (os.exists(dataFile)) read[Map[String, UserData]](os.read(dataFile))
  else Map()
}

def saveUsers(users: Map[String, UserData]): Unit = os.write.over(dataFile, write(users, indent = 2), createFolders = true)

def saveGame(user: UserData, game: GameState, users: Map[String, UserData]): Map[String, UserData] = {
  val updatedUser = user.copy(games = user.games :+ game)
  val updatedUsers = users.updated(user.username, updatedUser)
  saveUsers(updatedUsers)
  updatedUsers
}

def loadMultiplayerData(): Map[String, List[String]] = {
  if (os.exists(multiplayerDataFile)) read[Map[String, List[String]]](os.read(multiplayerDataFile))
  else Map()
}

def saveMultiplayerData(data: Map[String, List[String]]): Unit = {
  os.write.over(multiplayerDataFile, write(data, indent = 2), createFolders = true)
}

def prompt(message: String): String = {
  print(s"$message ")
  readLine()
}