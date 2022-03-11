package exception;

public class FilterIsNotSupportedException extends RuntimeException {
    public FilterIsNotSupportedException(String msg) {
        super(msg);
    }
}