package common.utils;

import io.qameta.allure.Step;
import net.datafaker.Faker;

import java.util.Locale;

public final class TestData {

    private static final Faker FAKER = new Faker(new Locale("ru"));
    private static final ThreadLocal<String> LAST_PASSWORD = new ThreadLocal<>();

    private TestData() {
    }

    @Step("Создание рандомного имени")
    public static String randomFirstName() {
        return FAKER.name().firstName();
    }

    @Step("Создание рандомной фамилии")
    public static String randomLastName() {
        return FAKER.name().lastName();
    }

    @Step("Создание рандомной почты")
    public static String randomEmail() {
        return FAKER.internet().emailAddress();
    }

    @Step("Создание рандомного логина")
    public static String randomLogin() {
        return "user_" + FAKER.regexify("[a-z0-9]{8}");
    }

    /**
     * Генерирует новый пароль и запоминает его как «текущий».
     * Последующий вызов confirmRandomPassword() вернёт то же самое значение.
     */

    @Step("Создание рандомного пароля")
    public static String randomPassword() {
        String password = FAKER.credentials().password(8, 16, true, false, true);
        LAST_PASSWORD.set(password);
        return password;
    }

    /**
     * Возвращает пароль, сгенерированный последним вызовом randomPassword().
     * Бросает IllegalStateException, если randomPassword() ещё не вызывался.
     */
    @Step("Подтверждение рандомного пароля")
    public static String confirmRandomPassword() {
        String password = LAST_PASSWORD.get();
        if (password == null) {
            throw new IllegalStateException(
                    "confirmRandomPassword() вызван до randomPassword() — нечего подтверждать");
        }
        return password;
    }
}