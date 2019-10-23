package nz.co.spark.springrestsecurity.error;

public class BookNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8675422195738674930L;

    public BookNotFoundException(final Long id) {
        super("Book id not found : " + id);
    }
}
