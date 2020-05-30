import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    /* ArrayList[row][column].
       This is used to initialize a graph that is len_x cells wide and
       len_y cells high. (0,0) corresponds to top left corner.
       @params = len_x : number of x coordinates.
                 len_y : number of y coordinates.
       @returns = arraylist of nodes where none of the nodes have
                  any connected edges.
       @throws = IndexOutOfBoundsException if len_x or len_y is too large.
     */
    public ArrayList<ArrayList<Node>> init_graph(int len_x, int len_y) {

        if (len_x >= 40 || len_y >= 40) {
            // Out of bounds len_y or len_y.
            throw new IndexOutOfBoundsException("Maze parameters are too large.");
        }

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        // For now len_y == len_x. So arraylist is square.
        // i = row count, j = col count.
        for (int i = 0; i != len_x; ++i) {
            ArrayList<Node> row = new ArrayList<>();
            for (int j = 0; j != len_y; ++j) {
                // [x+1,y] [x-1,y] [x,y+1] [x,y-1] == neighbours.
                ArrayList<ArrayList<Integer>> neighbours = new ArrayList<>();

                if (i+1 < len_x) {
                    // [x+1,y] is in range.
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i+1);
                    temp.add(j);
                    neighbours.add(temp);
                }
                if (i-1 >= 0) {
                    // [x-1,y] is in range.
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i-1);
                    temp.add(j);
                    neighbours.add(temp);
                }
                if (j+1 < len_y) {
                    // [x,y+1] is in range.
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j+1);
                    neighbours.add(temp);
                }
                if (j-1 >= 0) {
                    // [x,y-1] is in range.
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j-1);
                    neighbours.add(temp);
                }

                Node node = new Node(i, j, neighbours);
                row.add(node);
            }
            graph.add(row);
        }

        return graph;
    }

    /* This function does most of the work. It builds the maze using a depth first search
       algorithm. It doesn't actually build the visual display of the maze. Only adds edges
       to the nodes indicating the path.
       @params = graph : 2d arraylist of nodes with x x coordinates and y y coordinates.
       @returns = same graph but the nodes are updated to have edges.
       MAKE SURE TO CATCH STACKEXCEPTION
     */
    public ArrayList<ArrayList<Node>> build_maze(ArrayList<ArrayList<Node>> graph) {
        // First pick starting and end cells.
        // From start cell do depth first search.
        // Do depth first search until stack is empty.
        ArrayList<Integer> start = generate_start(graph.size(), graph.get(0).size());
        ArrayList<Integer> end = generate_end(graph.size(), graph.get(0).size(), start);

        System.out.println("Start: " + start.get(0) + ", " + start.get(1));
        System.out.println("End: " + end.get(0) + ", " + end.get(1));
        // Set nodes at graph [start_x, start_y] as starting or finishing nodes.
        graph.get(start.get(0)).get(start.get(1)).start_or_end();
        graph.get(end.get(0)).get(end.get(1)).start_or_end();

        Stack stack = new Stack();
        stack.push(start);
        Node current = graph.get(start.get(0)).get(start.get(1));

        // Loop until you backtrack to start aka stack is empty.
        while (!stack.is_empty())  {
            // Depth first search until you encounter a cell where all its neighbours
            // are visited. Then using stack back track until you reach a node that
            // has unvisited neighours. in the process pop the nodes off.
            dfs(current, stack, graph);
        }

        return graph;
    }

    /* This is a depth first search algorithm.
       @params = at : node we are currently at.
                 stack : stack keeping track of nodes we visited so we can backtrack.
     */
    private void dfs(Node at, Stack stack, ArrayList<ArrayList<Node>> graph) {

        if (at.is_visited()) {
            // This node has already been visited. Pop it off stack and backtrack.
            stack.pop();
            return;
        }

        at.visited();
        // Pick random neighbour and do dfs on it. Repeat until all neighbours have been visited.
        while (!at.is_empty()) {
            // Coordinates of neighbour picked randomly.
            ArrayList<Integer> random_neigh = at.get_random();
            // Get corresponding node.
            Node next = graph.get(random_neigh.get(0)).get(random_neigh.get(1));
            // Make sure to push to stack and update edge to.
            stack.push(random_neigh);
            at.add_edge(next);
            dfs(next, stack, graph);
        }
    }

    /* First randomly decide if x is going to be 0 or nonzero.
       If x == 0 then y can be nonzero and vice versa.
       @returns = arraylist containing [x,y] coordinates.
     */
    private ArrayList<Integer> generate_start(int row_count, int col_count) {

        ArrayList<Integer> ret = new ArrayList<>();
        Random random = new Random();
        boolean is_zero = random.nextBoolean();

        if (is_zero) {
            // X == 0 and y is nonzero.
            ret.add(0);
            Integer y = random.nextInt(col_count);
            ret.add(y);

        }
        else {
            // x is nonzero and y == 0.
            Integer x = random.nextInt(row_count);
            ret.add(x);
            ret.add(0);
        }

        return ret;
    }

    /* Generates end cell similarly to generate_start but makes sure
       it doesnt make the maze end at the same place as start.
       @params = start : arraylist containing coordinates of start.
       @returns = arraylist containing coordinates of end cell.
     */
    private ArrayList<Integer> generate_end(int row_count, int col_count, ArrayList<Integer> start) {

        ArrayList<Integer> ret = new ArrayList<>();
        Random random = new Random();
        boolean is_zero = random.nextBoolean();

        if (is_zero) {
            ret.add(0);
            Integer y = start.get(1);

            if (start.get(0) == 0) {
                // Starting x coordinate == 0.
                while (y == start.get(1)) {
                    // Until you get y that isnt equal to starting y.
                    y = random.nextInt(col_count);
                }
            }
            else {
                y = random.nextInt(col_count);
            }

            ret.add(y);
        }
        else {
            Integer x = start.get(0);
            Integer y = 0;

            if (start.get(1) == 0) {
                // y == 0 so generate x until you get x that isnt same as starting x.
                while (x == start.get(0)) {
                    x = random.nextInt(row_count);
                }
            }
            else {
                x = random.nextInt(row_count);
            }

            ret.add(x);
            ret.add(y);
        }

        return ret;
    }


    private void run_tests() {

        Tests test = new Tests();
        try {
            System.out.println("Testing init_graph function.");
            test.test_init_graph(this);
            System.out.println("\nTesting build_maze function.");
            test.test_build_maze(this);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        Main obj = new Main();
        obj.run_tests();
    }
}
