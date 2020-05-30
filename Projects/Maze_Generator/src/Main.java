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

        // Set nodes at graph [start_x, start_y] as starting or finishing nodes.
        graph.get(start.get(0)).get(start.get(1)).start_or_end();
        graph.get(end.get(0)).get(end.get(1)).start_or_end();

        Stack stack = new Stack();
        stack.push(start);
        Node current = graph.get(start.get(0)).get(start.get(1));
        dfs(current, stack, graph);

        System.out.println("Start: " + start.get(0) + ", " + start.get(1));
        System.out.println("End: " + end.get(0) + ", " + end.get(1));

        // Loop until you backtrack to start aka stack is empty.
        //while (!stack.is_empty())  {
        // Depth first search until you encounter a cell where all its neighbours
        // are visited. Then using stack back track until you reach a node that
        // has unvisited neighours. in the process pop the nodes off.
        //   dfs(current, stack, graph);
        //}

        return graph;
    }

    private void dfs(Node start, Stack stack, ArrayList<ArrayList<Node>> graph) {

        start.visited();
        while (!stack.is_empty()) {
            ArrayList<Integer> cur = stack.pop();
            Node cur_node = graph.get(cur.get(0)).get(cur.get(1));

            ArrayList<Integer> get_unvisited_neigh = cur_node.get_random_unvisited(graph);
            if (get_unvisited_neigh != null) {
                stack.push(cur);
                Node neighbour = graph.get(get_unvisited_neigh.get(0)).get(get_unvisited_neigh.get(1));
                cur_node.add_edge(neighbour);
                neighbour.visited();
                stack.push(get_unvisited_neigh);
            }
        }
    }


    /* Works by deciding which side the entry will be then randomly generating
       the free coordinate.
       @params = row_count : number of rows.
                 col_count : number of columns.
       @returns = arraylist containing [x,y,side] coordinates.
     */
    private ArrayList<Integer> generate_start(int row_count, int col_count) {

        ArrayList<Integer> ret = new ArrayList<>();
        Random random = new Random();
        int side = random.nextInt(4);

        switch (side) {
            // Number from 0 - 3 corresponds to which side the start will be.
            // 1 == leftmost side, 0 = top side, 3 = right side, 2 = bottom.
            case 0 : ret.add(0); ret.add(random.nextInt(col_count)); ret.add(0); break;
            case 1 : ret.add(random.nextInt(row_count)); ret.add(0); ret.add(1); break;
            case 2 : ret.add(random.nextInt(row_count)); ret.add(col_count - 1); ret.add(2); break;
            case 3 : ret.add(row_count - 1); ret.add(random.nextInt(col_count)); ret.add(3); break;
            default : break;
        }

        return ret;
    }

    /* Generates end cell similarly to generate_start but makes sure
       it doesnt make the maze end at the same place as start or near to it.
       For now we will try to make it land it on a different side to the start.
       @params = start : arraylist containing coordinates of start.
       @returns = arraylist containing coordinates of end cell.
     */
    private ArrayList<Integer> generate_end(int row_count, int col_count, ArrayList<Integer> start) {

        ArrayList<Integer> ret = generate_start(row_count, col_count);

        while (ret.get(2).equals(start.get(2))) {
            // If they are on same side.
            ret = generate_start(row_count, col_count);
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
