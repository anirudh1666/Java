/* This class is used to hold cells that have been visited.
   Could've implemented it using loops or recursion but recursion
   may cause stack overflow and we want to make sure it is last in
   first out.
 */

import java.util.ArrayList;

public class Stack {

    // Holds {{x,y}......} for the graph.
    private ArrayList<ArrayList<Integer>> stack;

    public Stack() {
        this.stack = new ArrayList<ArrayList<Integer>>();
    }

    public void push(ArrayList<Integer> cell) {

        this.stack.add(cell);
    }

    // Throws error if stack is empty and you try to pop.
    public ArrayList<Integer> pop() {

        if (stack.size() != 0) {
            // Make sure to remove last element as well as return it.
            int size = this.stack.size();
            ArrayList<Integer> ret = this.stack.get(size - 1);
            this.stack.remove(size - 1);
            return ret;
        } else {
            //throw new StackException("Trying to pop off empty stack.");
        }

        // Remove this code when you have stack exception.
        ArrayList<Integer> temp = new ArrayList<Integer>();
        return temp;
    }

    public boolean is_empty() {

        return this.stack.size() == 0;
    }
}
