package common.utils;

import java.text.Normalizer;
import com.ibm.icu.text.Transliterator;
import io.qameta.allure.Step;
import net.datafaker.Faker;

import java.util.Locale;

public final class TestData {

    private static final Faker FAKER = new Faker(Locale.of("ru"));
    private static final Transliterator CYR_LAT =
            Transliterator.getInstance("Cyrillic-Latin");
    private static int emailCounter = 0;
    private static String lastPassword;
    private static String lastFirstName;
    private static String lastLastName;

    private TestData() {
    }

    @Step("Создание рандомного имени")
    public static String randomFirstName() {
        String firstName = FAKER.name().firstName();
        lastFirstName = firstName;
        return firstName;
    }
    @Step("Подтверждение рандомного имени")
    public static String duplicateRandomFirstName() {
        if (lastFirstName == null) {
            throw new IllegalStateException(
                    "duplicateRandomFirstName() вызван до randomFirstName() — нечего подтверждать");
        }
        return lastFirstName;
    }

    @Step("Создание рандомной фамилии")
    public static String randomLastName() {
        String lastName = FAKER.name().lastName();
        lastLastName = lastName;
        return lastName;
    }

    @Step("Подтверждение рандомной фамилии")
    public static String duplicateRandomLastName() {
        if (lastLastName == null) {
            throw new IllegalStateException(
                    "duplicateRandomLastName() вызван до randomLastName() — нечего подтверждать");
        }
        return lastLastName;
    }

    @Step("Создание рандомной почты")
    public static String randomEmail() {
        return FAKER.internet().emailAddress() + emailCounter++;
    }

    @Step("Транслитерация '{text}' в латиницу")
    public static String toAsciiLatin(String text) {
        if (text == null) return null;

        // 1) кириллица → фонетическая латиница (может содержать диакритику: «Щука» → «Ŝuka», «Пётр» → «Pëtr»)
        String phonetic = CYR_LAT.transliterate(text);

        // 2) NFD раскладывает диакритику в отдельные комбинирующие символы и убираем их
        String decomposed = Normalizer.normalize(phonetic, Normalizer.Form.NFD);

        // 3) страховка: оставляем только латиницу/цифры (на случай апострофов из BGN, если вернётесь к нему)
        return decomposed.replaceAll("\\p{M}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Step("Создание рандомного логина")
    public static String randomLogin() {
        return "user_" + FAKER.regexify("[a-z0-9]{8}");
    }

    @Step("Создание рандомного пароля")
    public static String randomPassword() {
        String password = FAKER.credentials().password(8, 16, true, false, true);
        lastPassword = password;
        return password;
    }

    @Step("Подтверждение рандомного пароля")
    public static String confirmRandomPassword() {
        if (lastPassword == null) {
            throw new IllegalStateException(
                    "confirmRandomPassword() вызван до randomPassword() — нечего подтверждать");
        }
        String result = lastPassword;
        lastPassword = null;
        return result;
    }
}