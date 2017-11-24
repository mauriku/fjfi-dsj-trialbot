name "Kryten"
def n = 101
def maze = new Integer[n][n]
def xpos = 50;
def ypos = 50;
   for (int i = 0; i<n; i++){
       for (int j = 0; j<n; j++){
           maze[i][j]=0;
       }
   }
   def step = 0;
on TURN: {
    step = step+1;
    println(step);





       },
do: {

  if (!RIGHT.notWall){
    maze[xpos+1][ypos] = -1;
  }
  if (!LEFT.notWall){
      maze[xpos-1][ypos] = -1;
  }
  if (!UP.notWall){
      maze[xpos][ypos+1] = -1;
  }
  if (!DOWN.notWall){
      maze[xpos][ypos-1] = -1;
  }

 def toGo = [RIGHT, LEFT, UP, DOWN].findAll {dir -> dir.notWall && dir.notBeenTo}
   if (toGo.empty)
     toGo = [RIGHT, LEFT, UP, DOWN].findAll {dir -> dir.notWall}
     def value = 1000000;
     def correct;
     toGo = toGo.each {dir ->
            def x = 0;
            def y = 0;
         switch(dir){
            case RIGHT:
                x = xpos + 1;
                y = ypos;
                if(maze[x][y] < value){
                    value = maze[x][y]
                    correct = RIGHT;
                }
                break;
            case LEFT:
                x = xpos - 1;
                y = ypos;
                if(maze[x][y] < value){
                                    value = maze[x][y]
                                    correct = LEFT;
                                }
                break;
            case UP:
                 y = ypos + 1;
                 x = xpos;
                 if(maze[x][y] < value){
                                     value = maze[x][y]
                                     correct = UP;
                                 }
                 break;
            case DOWN:
                 y = ypos - 1;
                 x = xpos;
                 if(maze[x][y] < value){
                                     value = maze[x][y]
                                     correct = DOWN;
                                     }
                 break;
           }
     }
     //toGo = toGo[0];
     toGo = correct;

     maze[xpos][ypos]=step;



   switch(toGo){
    case RIGHT:
        xpos = xpos + 1;
        break;
    case LEFT:
        xpos = xpos - 1;
        break;
    case UP:
         ypos = ypos + 1;
         break;
    case DOWN:
         ypos = ypos - 1;
         break;
   }

   go toGo
}