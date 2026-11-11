package common.api.errors;

public enum InvalidEmailApiError implements ApiError {
    NO_AT_SYMBOL  (400, "INVALID_EMAIL", "Некорректный email (нет @ и домена)"),
    NO_DOMAIN     (400, "INVALID_EMAIL", "Некорректный email (нет домена)"),
    DOUBLE_AT     (400, "INVALID_EMAIL", "Некорректный email (две @)"),
    SPACE_INSIDE  (400, "INVALID_EMAIL", "Некорректный email (пробел в локальной части)");

    private final int status;
    private final String error;
    private final String message;

    InvalidEmailApiError(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    @Override
    public int httpStatus() {
        return status;
    }

    @Override
    public String error() {
        return error;
    }

    @Override
    public String message() {
        return message;
    }
}
