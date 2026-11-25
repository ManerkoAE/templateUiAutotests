package config;


public enum InvalidEmail {

    NO_AT_SYMBOL("Некорректный email (нет @ и домена)"),

    NO_DOMAIN("Некорректный email (нет домена)"),

    DOUBLE_AT("Некорректный email (две @)"),

    SPACE_INSIDE("Некорректный email (пробел в локальной части)");

    private final String message;

    InvalidEmail(String message) {
        this.message = message;
    }

    public String getExpectedMessage() {
        return message;
    }
}