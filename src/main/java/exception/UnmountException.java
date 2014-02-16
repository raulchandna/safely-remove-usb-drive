package exception;

public class UnmountException extends Exception {

    public UnmountException(Exception exception) {
        super(exception);
    }
}
