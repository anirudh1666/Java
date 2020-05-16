/* This is a generic binary tree data structure made by Anirudh Lakra.
 */


class Node<T extends Comparable<T>> {

    T value;
    Node<T> left, right;

    public Node(T value, Node<T> left, Node<T> right) {

        this.value = value;
        this.left = left;
        this.right = right;
    }

    public T get_value() {

        return this.value;
    }

    public Node<T> get_left() {

        return this.left;
    }

    public Node<T> get_right() {

        return this.right;
    }

    public void set_left(T value) {

        Node<T> node = new Node<T>(value, null, null);
        this.left = node;
    }

    public void set_right(T value) {

        Node<T> node = new Node<T>(value, null, null);
        this.right = node;
    }
}


public class BinaryTree<T extends Comparable<T>> {

    Node<T> root;

    public BinaryTree(T root) {

        Node<T> root_node = new Node<T>(root, null, null);
        this.root = root_node;
    }

    public void add(T val) {

        if (this.root == null) {
            Node<T> node = new Node<T>(val, null, null);
            this.root = node;
        }
        else {
            this.add_auxiliary(this.root, val);
        }
    }

    private void add_auxiliary(Node<T> cur_node, T val) {

        if (cur_node == null) {
            cur_node = new Node<T>(val, null, null);
        }
        else {
            if (cur_node.get_value().compareTo(val) < 0) {
                if (cur_node.get_right() == null) {
                    cur_node.set_right(val);
                    return;
                }
                else {
                    this.add_auxiliary(cur_node.get_right(), val);
                }
            }
            else {
                if (cur_node.get_left() == null) {
                    cur_node.set_left(val);
                    return;
                }
                else {
                    this.add_auxiliary(cur_node.get_left(), val);
                }
            }
        }
    }


    private void print_auxiliary(Node<T> cur_node) {

        if (cur_node.get_left() != null)
            this.print_auxiliary(cur_node.get_left());
        System.out.println(cur_node.get_value());
        if (cur_node.get_right() != null)
            this.print_auxiliary(cur_node.get_right());
    }

    private void print() {

        if (this.root.get_left() != null)
            this.print_auxiliary(this.root.get_left());
        System.out.println(this.root.get_value());
        if (this.root.get_right() != null)
            this.print_auxiliary(this.root.get_right());
    }

    private void test_tree(BinaryTree<Integer> tree) {

        tree.add(60);
        tree.add(3);
        tree.add(39);
        tree.print();
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<Integer>(50);
        tree.test_tree(tree);
    }
}
