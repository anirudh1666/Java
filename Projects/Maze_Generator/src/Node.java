/* Node represents a cell in the 2D arraylist we use to represent
   the maze. Each node contains information about its edges, coordinates
   and if it has been visited yet.
 */

import java.util.ArrayList;

public class Node {

    private int x_coord;
    private int y_coord;
    private boolean visited;
    private boolean start_or_end;
    private ArrayList<Node> edges;

    public Node(int x, int y) {

        this.x_coord = x;
        this.y_coord = y;
        this.edges = new ArrayList<Node>();
        visited = false;
        start_or_end = false;
    }

    public int get_x() {

        return this.x_coord;
    }

    public int get_y() {

        return this.y_coord;
    }

    public void visited() {

        this.visited = true;
    }

    public ArrayList<Node> get_edges() {

        return this.edges;
    }

    public void start_or_end() {

        this.start_or_end = true;
    }
}
