/* This class holds the tests to test that our maze generator
   is working as intended.
 */

import java.util.ArrayList;


public class Tests {

    public void test_build_maze(Main obj) {

        ArrayList<ArrayList<Node>> graph = obj.init_graph(10,10);
        obj.build_maze(graph);
        print_graph(graph);
    }

    public void test_init_graph(Main obj) {

        ArrayList<ArrayList<Node>> graph = obj.init_graph(5,5);
        print_graph(graph);
    }

    private void print_graph(ArrayList<ArrayList<Node>> graph) {

        for (int i = 0; i != graph.size(); ++i) {
            // Iterate through each row.
            for (int j = 0; j != graph.get(0).size(); ++j) {
                // Iterate through column.
                Integer x = graph.get(i).get(j).get_x();
                Integer y = graph.get(i).get(j).get_y();
                ArrayList<Node> edges = graph.get(i).get(j).get_edges();

                String tuple = "(".concat(x.toString()).concat(", ").concat(y.toString()).concat("),");
                System.out.print(tuple + " Paths: ");

                for (Node edge : edges) {
                    // Build string with info about paths.
                    Integer edge_x = edge.get_x();
                    Integer edge_y = edge.get_y();
                    String path = "(".concat(edge_x.toString()).concat(", ").concat(edge_y.toString()).concat(") ");
                    System.out.print(path);
                }
                System.out.println();
            }
        }
    }
}
