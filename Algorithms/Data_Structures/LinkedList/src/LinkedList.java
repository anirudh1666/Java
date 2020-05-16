/* This is a generic implementation of a doubly linked list by
   Anirudh Lakra.
 */

// Auxiliary class for linked list.
class Node<T extends Comparable<T>> {

    private T value;
    private Node<T> before, next;

    public Node(T value) {

        this.value = value;
    }

    public Node(T value, Node next) {

        this.value = value;
        this.next = next;
    }

    public Node(T value, Node next, Node before) {

        this.value = value;
        this.next = next;
        this.before = before;
    }

    public T get_value() {

        return this.value;
    }

    public Node<T> get_next() {

        return this.next;
    }

    public Node<T> get_before() {

        return this.before;
    }

    public void set_next(Node<T> next) {

        this.next = next;
    }

    public void set_before(Node<T> before) {

        this.before = before;
    }

}

public class LinkedList<T extends Comparable<T>> {

    private Node<T> head;

    public LinkedList(Node<T> head) {

        this.head = head;
    }

    public void add(T value) {

        Node<T> cur_node = this.head;

        // Linked list consists of only the head.
        if (cur_node.get_next() == null) {
            Node<T> next = new Node<T>(value,null,cur_node);
            cur_node.set_next(next);
        }
        else {
            while (cur_node.get_next() != null)
                cur_node = cur_node.get_next();

            Node<T> next = new Node<T>(value, null, cur_node);
            cur_node.set_next(next);
        }
    }

    public void remove(T value) {

        if (this.head.get_value() == value) {
            this.head = this.head.get_next();
            return;
        }
        else {
            Node<T> cur_node = this.head;
            Node<T> prev_node = cur_node;

            while (cur_node.get_next() != null) {
                prev_node = cur_node;
                cur_node = cur_node.get_next();

                // If value is found.
                if (cur_node.get_value() == value) {

                    // Check if it is at the end.
                    if (cur_node.get_next() == null) {
                        prev_node.set_next(null);
                        cur_node.set_before(null);
                        return;
                    }

                    // If it is found in the middle.
                    else {
                        Node<T> next = cur_node.get_next();
                        cur_node.set_next(null);
                        cur_node.set_before(null);
                        prev_node.set_next(next);
                        next.set_before(prev_node);
                        return;
                    }
                }
            }
        }
    }

    public void print() {

        Node<T> cur_node = this.head;

        System.out.println("Head: " + cur_node.get_value().toString());
        while (cur_node.get_next() != null) {
            cur_node = cur_node.get_next();
            System.out.println("At: " + cur_node.get_value().toString());
        }
    }

    public void test_linked_list(LinkedList<String> list) {
        list.add("Hugo");
        list.add("Jack");
        list.add("Nimit");

        list.print();
        System.out.println();
        list.remove("Jack");
        list.print();
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>(new Node("Anirudh"));
        list.test_linked_list(list);
    }
}
