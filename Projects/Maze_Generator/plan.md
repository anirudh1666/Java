## MAZE GENERATOR ##

**Maze generator** is the precursor program for the Maze Generator web application.

The main aims for this project is to:

- Learn how maze generation algorithms work and implement one.
- Figure out how to display maze in console.
- Add functionality. For example, it should be possible for the user to configure the maze from terminal. Can choose maze difficulty, size and ask for maze to reveal solution.


## Algorithm ##

**Depth first search with explicit stack**

The algorithm I have used works by initiating a depth first search from the start node. Each time it visits a cell that hasn't been visited before it pushes it onto the stack. To prevent stack overflow, we create an explicit stack data structure that is used to hold cell data.
You can find the algorithm on wikipedia [here](https://en.wikipedia.org/wiki/Maze_generation_algorithm).

**1)** Generate a random starting cell. Change its member variable to visited = true and push it onto stack.

**2)** Loop while the stack is not empty.

**2.1)** Pop stack and find a random unvisited neighbour of the cell. If it has none then do nothing and pop next cell off stack.

**2.2)** If you found a neighbour cell, then add an edge from previous cell to neighbour cell and also push previous cell onto stack again (makes sure cell is on stack until all its neighbours are visited). Mark neighbour cell as visited and push it onto stack.



## V1.0 29/05/2020 ##

**IN ALL VERSIONS X COORDINATES REPRESENT ROWS WHILE Y COORDINATES REPRSENT COLUMNS. THIS IS OPPOSITE OF WHAT IT USUALLY REPRESENTS EG [3,8] MEANS 3RD ROW 8TH COLUMN.**

**Aims:**

- Generate random 5x5 maze in terminal.
- We make sure maze is square not rectangular for now.
- Additionally, the maze paths will only go top, bottom, left or right for now.

**Result** 
- Managed to make a representation of the maze in the form of a 2D arraylist of nodes. However, not sure yet how to display the maze to the user.