package common.api.errors;

public interface ApiError {
    int httpStatus();       // 400, 401, 404 ...
    String error();          // "INVALID_EMAIL", "UNAUTHORIZED" ...
    String message();       // человекочитаемое сообщение (для аттачмента)


}
