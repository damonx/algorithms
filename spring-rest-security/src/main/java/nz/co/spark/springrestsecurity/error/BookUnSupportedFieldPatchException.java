package nz.co.spark.springrestsecurity.error;

import java.util.Set;

public class BookUnSupportedFieldPatchException extends RuntimeException {

    private static final long serialVersionUID = -1271315615173062656L;

    public BookUnSupportedFieldPatchException(final Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }
}
