/* Node represents a cell in the 2D arraylist we use to represent
   the maze. Each node contains information about its edges, coordinates
   and if it has been visited yet.
 */

import java.util.Random;
import java.util.ArrayList;

public class Node {

    // (x,y) coordinates. X = row number, y = column number.
    private int x_coord;
    private int y_coord;
    // If this has been visited before.
    private boolean visited;
    // If this is start or end node.
    private boolean start_or_end;
    // An edge represents path from this node to edge node.
    private ArrayList<Node> edges;
    // Arraylist of [x,y] coordinates of its unvisited neighbouring cells.
    // Has [[x-1,y], [x,y-1], [x+1, y], [x, y+1].
    private ArrayList<ArrayList<Integer>> neighbours;

    public Node(int x, int y, ArrayList<ArrayList<Integer>> neighbours) {

        this.x_coord = x;
        this.y_coord = y;
        this.edges = new ArrayList<Node>();
        this.visited = false;
        this.start_or_end = false;
        this.neighbours = neighbours;
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

    public boolean is_visited() {

        return this.visited;
    }

    public ArrayList<Node> get_edges() {

        return this.edges;
    }

    public void start_or_end() {

        this.start_or_end = true;
    }

    public ArrayList<Integer> get_random_unvisited(ArrayList<ArrayList<Node>> graph) {

        ArrayList<Integer> neighbour = new ArrayList<>();
        Random random = new Random();
        boolean found = false;

        while (!found) {

            if (this.neighbours.size() == 0) {
                // No unvisited neighbours left.
                return null;
            }

            int index = random.nextInt(this.neighbours.size()) & Integer.MAX_VALUE;
            neighbour = this.neighbours.get(index);
            this.neighbours.remove(index);

            // Check if it is visited or not.
            if (!graph.get(neighbour.get(0)).get(neighbour.get(1)).is_visited()) {
                found = true;
            }
        }

        return neighbour;
    }


    public void add_edge(Node node) {

        this.edges.add(node);
    }
}
