name "RightHand of the Devil"

on TURN: {}, do: {
    foundWall = false;
    lastKnownWall = RIGHT;
    lastMove = [];

    def wall = [RIGHT, LEFT, UP, DOWN].findAll {dir -> dir.wall};

    if( !wall.contains(lastKnownWall) ) {
        go lastKnownWall;
    }
    else if( wall.empty && !foundWall ) {
        go UP;
    }
    else
    {
        def up    = [RIGHT, UP   , LEFT , DOWN ];
        def down  = [LEFT , DOWN , RIGHT, UP   ];
        def right = [DOWN , RIGHT, UP   , LEFT ];
        def left  = [UP   , LEFT , DOWN , RIGHT];
        def togo = [UP];
        switch (lastMove)
        {
            case UP:
                toGo = up;
                break;
            case DOWN:
                toGo = down;
                break;
            case RIGHT:
                toGo = right;
                break;
            case LEFT:
                toGo = left;
                break;
            default:
                toGo = up;
                break;
        }

        toGo = (toGo - wall);
        lastMove = toGo[0];
        switch (lastMove) {
            case UP:
                lastKnownWall = RIGHT;
            case DOWN:
                lastKnownWall = LEFT;
            case LEFT:
                lastKnownWall = UP;
            case RIGHT:
                lastKnownWall = DOWN;
        }
        go toGo[0];
    }
}