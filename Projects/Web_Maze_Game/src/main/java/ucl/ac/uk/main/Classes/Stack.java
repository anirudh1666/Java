package ucl.ac.uk.main.Classes;

import java.util.ArrayList;


/* This is a stack data structure I made to help me with
   the recursive depth first search algorithm used to build
   the maze.
 */
public class Stack {

    // Holds a List of [row, col] form.
    private ArrayList<ArrayList<Integer>> stack;

    public Stack() {
        this.stack = new ArrayList<>();
    }

    // Pushes cell on to the end of this.stack.
    public void push(ArrayList<Integer> cell) {

        this.stack.add(cell);
    }

    /* Used to get the last element from this.stack while removing it.
       @returns = last cell.
       @throws = StackException if you try to pop off empty stack.
     */
    public ArrayList<Integer> pop() throws StackException {

        if (stack.size() != 0) {
            int size = this.stack.size();
            ArrayList<Integer> ret = this.stack.get(size - 1);
            this.stack.remove(size - 1);
            return ret;
        }
        else {
            throw new StackException("Trying to pop off empty stack.");
        }
    }

    // Returns true if stack is empty.
    public boolean empty() {

        return this.stack.size() == 0;
    }
}
