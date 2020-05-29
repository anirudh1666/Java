/* This class holds the tests to test that our maze generator
   is working as intended.
 */

import java.util.ArrayList;


public class Tests {

    public void test_build_maze(Main obj) {

        ArrayList<ArrayList<Node>> graph = obj.init_graph(5,5);
        obj.build_maze(graph);
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

                String tuple = "(".concat(x.toString()).concat(", ").concat(y.toString()).concat(")");
                System.out.println(tuple);
            }
        }
    }
}
