package org.labmonkeys.home_library.bookshelf;

public class BookShelfException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BookShelfException() {
    }

    public BookShelfException(String message) {
        super(message);
    }

    public BookShelfException(Throwable cause) {
        super(cause);
    }

    public BookShelfException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookShelfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
