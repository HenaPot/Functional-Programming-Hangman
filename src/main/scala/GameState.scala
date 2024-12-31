import upickle.default._

case class GameState(
    word: String,
    guessesLeft: Int,
    hangmanState: Int,
    pooledWordSet: Set[Char] = Set(),
    wordSet: Set[Char] 
) 
object GameState{
  implicit val rw: ReadWriter[GameState] = macroRW
}
