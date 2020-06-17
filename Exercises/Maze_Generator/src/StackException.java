/* Main focus of this class is to provide exceptions for our
   stack class.
 */


public class StackException extends Exception {

    public StackException() {
        super("Default message");
    }

    public StackException(String s) {
        super(s);
    }
}
