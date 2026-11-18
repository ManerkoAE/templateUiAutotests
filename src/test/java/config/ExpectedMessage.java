package config;

public enum ExpectedMessage {

    WRONG_CREDENTIALS("Неверные учетные данные"),
    USERNAME_ALREADY_TAKEN("Имя пользователя уже занято");

    private final String text;

    ExpectedMessage(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
