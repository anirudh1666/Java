/* Graph is represented as an double[] of the edges. Each edge contains
   details about its weight and nodes it comes from and goes to. Edge is
   represented by a nested static class Edge.
 */

public class bellmanford {

    public double[] run_bf(int start_node, int num_of_nodes, Edge[] graph) {

        /* Distance holds distances to other nodes from the start node.
           The index indicates which node to. E.g. distance[3] is distance from
           start_node to node 3. */
        double[] distance = init_distance(num_of_nodes);
        distance[start_node] = 0;

        // Loop as many times as # of nodes.
        for (int i = 0; i != num_of_nodes; ++i) {
            for (Edge e : graph) {
                double edge_weight = e.get_weight();
                int edge_to = e.get_to();
                int edge_from = e.get_from();

                /* If you can relax the distance[i] any further
                   do so. */
                if (distance[edge_from] + edge_weight < distance[edge_to]) {
                    distance[edge_to] = distance[edge_from] + edge_weight;
                }
            }
        }

        // Detect any negative weight cycles.
        for (int i = 0; i != num_of_nodes; ++i) {
            for (Edge e : graph) {
                double edge_weight = e.get_weight();
                int edge_to = e.get_to();
                int edge_from = e.get_from();

                // Negative Cycle detected.
                if (distance[edge_from] + edge_weight < distance[edge_to]) {
                    distance[edge_to] = Double.NEGATIVE_INFINITY;
                }
            }
        }

        return distance;
    }

    // Auxiliary method to help initialize array of large distances.
    private double[] init_distance(int num_of_nodes) {

        double[] ret = new double[num_of_nodes];
        for (int i = 0; i != num_of_nodes; ++i) {
            ret[i] = 100000000;
        }
        return ret;
    }

    private void test() {

        Edge[] graph = set_up_graph();
        int num_of_nodes = 10;
        double[] dist = run_bf(0, num_of_nodes, graph);

        System.out.println("Expected distance: [0, 5, -inf, -inf, -inf, 35, 40, -10, -20, -inf]");
        System.out.print("Calculated distance: [");
        for (int i = 0; i != dist.length - 1; ++i)
            System.out.print(dist[i] + ", ");
        System.out.println(dist[dist.length - 1] + "]");
    }

    private Edge[] set_up_graph() {

        Edge edge0 = new Edge(1, 0, 5);
        Edge edge1 = new Edge(2, 1, 20);
        Edge edge2 = new Edge(3, 2, 10);
        Edge edge3 = new Edge(2, 3, -15);
        Edge edge4 = new Edge(4, 2, 75);
        Edge edge5 = new Edge(9, 4, 100);
        Edge edge6 = new Edge(5, 1, 30);
        Edge edge7 = new Edge(6, 5, 5);
        Edge edge8 = new Edge(6, 1, 60);
        Edge edge9 = new Edge(7, 6, -50);
        Edge edge10 = new Edge(8, 7, -10);
        Edge edge11 = new Edge(8, 5, 50);
        Edge edge12 = new Edge(4, 5, 25);
        Edge[] graph = {edge0, edge1, edge2, edge3, edge4, edge5, edge6, edge7, edge8, edge9, edge10, edge11, edge12};
        return graph;
    }

    public static void main(String[] args) {
        bellmanford obj = new bellmanford();
        obj.test();
    }

    /* Inner class to help represent graph. Graph will be an arraylist of Edges or
       can use array.
     */
    public static class Edge {

        private int to;
        private int from;
        private double weight;

        public Edge(int to, int from, double weight) {

            this.to = to;
            this.from = from;
            this.weight = weight;
        }

        public int get_to() {

            return this.to;
        }

        public int get_from() {

            return this.from;
        }

        public double get_weight() {

            return this.weight;
        }
    }
}

