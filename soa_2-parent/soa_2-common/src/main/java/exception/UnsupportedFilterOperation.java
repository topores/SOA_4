package exception;

public class UnsupportedFilterOperation extends RuntimeException {
    public UnsupportedFilterOperation(String msg) {
        super(msg);
    }
}