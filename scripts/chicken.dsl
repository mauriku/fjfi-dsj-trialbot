name "Chicken"

on TURN: {}, do: {
  def toGo = [RIGHT, LEFT, UP, DOWN].findAll {dir -> dir.notWall && dir.notBeenTo}
  if (toGo.empty)
    toGo = [RIGHT, LEFT, UP, DOWN].findAll {dir -> dir.notWall}

  go toGo
}