case class GameState(
    guessesLeft: Int,
    hangmanState: Int,
    pooledWordSet: Set[Char] = Set(),
    wordSet: Set[Char] 
)
