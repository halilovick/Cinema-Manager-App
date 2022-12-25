package ba.unsa.etf.rpr.exceptions;

public class FilmoviException extends Exception {
    public FilmoviException(String m, Exception e) {
        super(m, e);
    }

    public FilmoviException(String m) {
        super(m);
    }
}
