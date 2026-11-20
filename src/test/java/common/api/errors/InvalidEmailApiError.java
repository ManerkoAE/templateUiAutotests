package common.api.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InvalidEmailApiError {

    NO_AT_SYMBOL(400, "INVALID_EMAIL", "Некорректный email (нет @ и домена)"),
    NO_DOMAIN(400, "INVALID_EMAIL", "Некорректный email (нет домена)"),
    DOUBLE_AT(400, "INVALID_EMAIL", "Некорректный email (две @)"),
    SPACE_INSIDE(400, "INVALID_EMAIL", "Некорректный email (пробел в локальной части)");

    private final int status;
    private final String error;
    private final String message;
}

