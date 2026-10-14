package common.utils;

import net.datafaker.Faker;

import java.util.Locale;

public final class TestData {

    private static final Faker FAKER = new Faker(new Locale("ru"));
    private static final ThreadLocal<String> LAST_PASSWORD = new ThreadLocal<>();

    private TestData() {}

    public static String randomFirstName() {
        return FAKER.name().firstName();
    }

    public static String randomLastName() {
        return FAKER.name().lastName();
    }

    public static String randomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String randomLogin() {
        return "user_" + FAKER.regexify("[a-z0-9]{8}");
    }
    /**
     * Генерирует новый пароль и запоминает его как «текущий».
     * Последующий вызов confirmRandomPassword() вернёт то же самое значение.
     */
    public static String randomPassword() {
        String password = FAKER.credentials().password(8, 16, true, false, true);
        LAST_PASSWORD.set(password);
        return password;
    }

    /**
     * Возвращает пароль, сгенерированный последним вызовом randomPassword().
     * Бросает IllegalStateException, если randomPassword() ещё не вызывался.
     */
    public static String confirmRandomPassword() {
        String password = LAST_PASSWORD.get();
        if (password == null) {
            throw new IllegalStateException(
                    "confirmRandomPassword() вызван до randomPassword() — нечего подтверждать");
        }
        return password;
    }
}