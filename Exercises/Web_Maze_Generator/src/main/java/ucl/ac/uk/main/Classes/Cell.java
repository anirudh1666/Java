package ucl.ac.uk.main.Classes;

import java.util.ArrayList;
import java.util.Random;

/* This class represents a cell in the maze. Each cell
   holds information about its row number, col number,
   edges (paths to neighbouring cells), if it is start or
   end cell and unvisited neighbouring cells.
 */
public class Cell {

    private int row_num;
    private int col_num;
    private boolean start_or_end;
    // If it has an edge to a cell it means that there is a path to it.
    private ArrayList<ArrayList<Integer>> edges;
    // These are only unvisited neighbours. Initially, it should contain all neighbours.
    private ArrayList<ArrayList<Integer>> neighbours;
    // If cell has been visited or not.
    private boolean visited;

    public Cell(int row_num, int col_num, ArrayList<ArrayList<Integer>> neighbours) {

        this.row_num = row_num;
        this.col_num = col_num;
        this.start_or_end = false;
        this.edges = new ArrayList<>();
        this.neighbours = neighbours;
        this.visited = false;
    }

    // @returns = row_num.
    public int get_row() { return this.row_num; }

    // @returns = col_num.
    public int get_col() { return this.col_num; }

    // Sets cell as visited.
    public void visited() { this.visited = true; }

    // @returns = true if cell is visited else false.
    public boolean is_visited() { return this.visited; }

    // @returns = true if cell is start or end node.
    public boolean is_start_or_end() { return this.start_or_end; }

    // @returns = ArrayList of neighbouring cell that this cell has a path to.
    public ArrayList<ArrayList<Integer>> get_edges() { return this.edges; }

    // Sets this cell as a starting or end cell
    public void start_or_end() { this.start_or_end = true; }

    // Adds an [row_num, col_num] of neighbouring cell to this.edges.
    public void add_edge(ArrayList<Integer> cell) { this.edges.add(cell); }

    /* Used to randomly get an unvisited neighbour. Once you return them they have to be
       deleted so that the same neighour isn't returned multiple times.
       @params = maze : the 2D arraylist of cells that make up the maze.
       @returns = ArrayList = [row_num, col_num] of the random unvisited cell.
                  If no unvisited neighbours, it returns null.
     */
    public ArrayList<Integer> get_random_unvisited(ArrayList<ArrayList<Cell>> maze) {

        ArrayList<Integer> ret = new ArrayList<>();
        Random random = new Random();
        // Found becomes true when we have an unvisited neighbour.
        boolean found = false;

        while (!found) {

            if (this.neighbours.size() == 0) {
                // No more neighbours left to visit.
                return null;
            }

            /* This makes sure the index is positive. & Integer.MAX_VALUE zeros out the sign bit.
               The reason this is better than checking for positive and discarding if negative is that
               it makes the positive values be 2x as likely to come up as 0.
            */
            int index = random.nextInt(this.neighbours.size()) & Integer.MAX_VALUE;
            ret = this.neighbours.get(index);
            this.neighbours.remove(index);

            if (!maze.get(ret.get(0)).get(ret.get(1)).is_visited()) {
                // Check if the cell at maze[row_num, col_num] has been visited. If not visited then return it.
                found = true;
            }
        }

        return ret;
    }

}
