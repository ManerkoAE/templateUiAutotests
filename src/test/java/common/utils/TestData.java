package common.utils;

import net.datafaker.Faker;

import java.util.Locale;

public final class TestData {

    private static final Faker FAKER = new Faker(new Locale("ru"));

    private TestData() {}

    public static String randomLogin() {
        return "user_" + FAKER.regexify("[a-z0-9]{8}");
    }

    public static String randomPassword() {
        return FAKER.credentials().password(8, 16, true, false, true);
    }
    public static String randomEmail() {
        return FAKER.internet().emailAddress();
    }
}