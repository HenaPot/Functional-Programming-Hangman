import upickle.default._

case class UserData(
    username: String, 
    password: String, 
    games: List[GameState] = List.empty
)

object UserData{
  implicit val rw: ReadWriter[UserData] = macroRW
}
