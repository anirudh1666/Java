package ucl.ac.uk.main.Classes;

import java.util.ArrayList;
import java.util.Random;

/* This class is responsible for generating the maze which it
   will send to the frontend. The maze is a 2D arraylist of cells
   that contain information about path_to and path_from.
 */
public class Maze {

    /* ArrayList[row][column]. This is used to initialize a 2D arraylist
       with len_x columns and len_y rows. (0,0) corresponds to top left corner.
       @params = len_x : number of cols.
                 len_y : number of rows.
       @returns = arraylist of cells where none of the cells have any connected
                  edges yet.
       @throws = IndexOutOfBoundsException if len_x or len_y are out of bounds.
     */
    private ArrayList<ArrayList<Cell>> init_graph(int len_x, int len_y) {

        if (len_x >= 40 || len_y >= 40 || len_x <= 0 || len_y <= 0) {
            // Out of bounds.
            throw new IndexOutOfBoundsException("Maze parameters are out of bounds.");
        }

        ArrayList<ArrayList<Cell>> maze = new ArrayList<>();

        for (int i = 0; i != len_y; ++i) {
            // i = row count.
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j != len_x; ++j) {
                // j = col count.

                ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>();

                // Adding all neighbours above, below, left and right if they are in bounds.
                if (i + 1 < len_y) {
                    // [y+1,x] is in range.
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i + 1);
                    temp.add(j);
                    neighbours.add(temp);
                }
                if (i - 1 >= 0) {
                    // [y-1,x] is in range.
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i - 1);
                    temp.add(j);
                    neighbours.add(temp);
                }
                if (j + 1 < len_x) {
                    // [y,x+1] is in range.
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j + 1);
                    neighbours.add(temp);
                }
                if (j - 1 >= 0) {
                    // [y,x-1] is in range.
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j - 1);
                    neighbours.add(temp);
                }

                Cell cell = new Cell(i, j, neighbours);
                row.add(cell);
            }

            maze.add(row);
        }

        return maze;
    }

    /* Firstly, we randomly generate starting and ending points. The only condition is that
       the start and end can't be on the same side. This helps stop them from being adjacent.
       Then it uses a depth first seach algorithm using a Stack. The DFS adds paths to the maze.
       @params = maze : maze that you want to add paths to.
       @returns = the same maze but now the cells contain information about edges to neighbours.
     */
    private ArrayList<ArrayList<Cell>> build_maze(ArrayList<ArrayList<Cell>> maze) {

        // ArrayList = [row, col, side]. Sides are number 0-3 going anticlockwise from top.
        ArrayList<Integer> start = generate_start(maze.size(), maze.get(0).size());
        ArrayList<Integer> end = generate_end(maze.size(), maze.get(0).size(), start);

        // Set the starting and ending cells member variable start_or_end = true.
        maze.get(start.get(0)).get(start.get(1)).start_or_end();
        maze.get(end.get(0)).get(end.get(1)).start_or_end();

        // Push start coords onto to stack and initiate DFS.
        Stack stack = new Stack();
        stack.push(start);
        Cell curr = maze.get(start.get(0)).get(start.get(1));
        dfs(curr, stack, maze);

        return maze;
    }

    /* Recursive depth first search using a stack. It works by popping off the stack
       and picking a random unvisited neighbour. If it doesn't have any you continue to
       the next cell by popping. Otherwise, you push neighbour and curr_cell to stack, mark neighbour as visited
       and add an edge from curr_cell to neigh_cell. Then continue. Repeat until stack is empty.
       @params = start : starting cell.
                 stack : stack that will hold coordinates of cells.
                 maze : 2d arraylist of cells in the maze.
     */
    private void dfs(Cell start, Stack stack, ArrayList<ArrayList<Cell>> maze) {

        start.visited();
        try {
            while (!stack.empty()) {
                ArrayList<Integer> curr = stack.pop();
                Cell curr_cell = maze.get(curr.get(0)).get(curr.get(1));

                ArrayList<Integer> unvisited_neigh = curr_cell.get_random_unvisited(maze);
                if (unvisited_neigh != null) {
                    // null is returned if curr_cell has no unvisited neighbours.
                    // Here we push curr_cell and unvisited_neigh. Also add edge from curr_cell to
                    // neigh and mark neigh as visited.
                    stack.push(curr);
                    Cell neighbour = maze.get(unvisited_neigh.get(0)).get(unvisited_neigh.get(1));

                    curr_cell.add_edge(unvisited_neigh);
                    neighbour.visited();
                    stack.push(unvisited_neigh);
                }
            }
        } catch (StackException e) {
            // Stack was empty but we tried to pop off it anyway. This shouldn't happen
            System.out.println(e);
        }
    }

    /* Works by deciding which side the start will be on then randomly generating
       the other free coordinate.
       @params = row_count : number of rows.
                 col_count : number of columns.
       @returns = arraylist containing [row, column, side].
     */
    private ArrayList<Integer> generate_start(int row_count, int col_count) {

        // Ret contains [row number, col number, side]
        ArrayList<Integer> ret = new ArrayList<>();
        Random random = new Random();
        // top = 0, left = 1, bottom = 2, right = 3.
        int side = random.nextInt(4);

        switch (side) {
            // topside. row = 0, column = random number.
            case 0 : ret.add(0); ret.add(random.nextInt(col_count)); ret.add(0); break;
            // leftside. row = random number, column = 0.
            case 1 : ret.add(random.nextInt(row_count)); ret.add(0); ret.add(1); break;
            // bottomside. row = max row num, column = random number.
            case 2 : ret.add(row_count - 1); ret.add(random.nextInt(col_count)); ret.add(2); break;
            // rightside. row = random. column = max column number.
            case 3 : ret.add(random.nextInt(row_count)); ret.add(col_count - 1); ret.add(3); break;
            default : System.out.println("Unable to generate start."); break;
        }

        return ret;
    }

    /* Generates end cell by using generate_start(). However, it keeps generating end cells until
       it generates a cell that isn't on the same side as the starting cell. This helps stop the
       start and end cells be too close to each other.
       @params = start : arraylist containing coordinates of start.
       @returns = arraylist containing coordinates of end cell.

     */
    private ArrayList<Integer> generate_end(int row_count, int col_count, ArrayList<Integer> start) {

        ArrayList<Integer> ret = new ArrayList<>();

        // Loops until the end cell is generated on the opposite side to start.
        do {
            ret = generate_start(row_count, col_count);
        } while (ret.get(2).equals(start.get(2)));

        return ret;
    }

    /* This method just builds the maze then returns it.
       @params = row_count : number of rows.
                 col_count : number of columns.
       @returns = maze with edges and cells.
     */
    public ArrayList<ArrayList<Cell>> maze(int row_count, int col_count) {

        ArrayList<ArrayList<Cell>> maze = init_graph(col_count, row_count);
        build_maze(maze);

        return maze;
    }
}
