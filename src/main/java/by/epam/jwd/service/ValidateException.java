package by.epam.jwd.service;

public class ValidateException extends Exception{

    private static final long serialVersionUID = 1L;

    private String localizedMessage;

    public ValidateException() { }

    @Override
    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public ValidateException(String message, String localizedMessage) {
        super(message);
        this.localizedMessage = localizedMessage;
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(Throwable cause) {
        super(cause);
    }

    protected ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
