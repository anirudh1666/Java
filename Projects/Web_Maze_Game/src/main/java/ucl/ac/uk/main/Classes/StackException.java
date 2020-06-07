package ucl.ac.uk.main.Classes;

/* Main aim of this class is to provide an exception class
   for the stack data structure.
 */
public class StackException extends Exception {

    // No message given, display defalut message.
    public StackException() {
        super("Default message");
    }

    // If message is given, display it.
    public StackException(String s) {
        super(s);
    }
}
