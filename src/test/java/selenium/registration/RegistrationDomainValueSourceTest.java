package selenium.registration;

import common.selenium.pages.LoginPage;
import common.selenium.pages.RegistrationPage;
import common.utils.TestData;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import selenium.BaseTest;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationDomainValueSourceTest extends BaseTest {

    @ParameterizedTest(name = "Позитивный тест: Регистрация с доменом {0} завершается успешно")
    @Description("Проверка, что при использовании валидного email (разных доменов) регистрация завершается без ошибок")
    @Severity(SeverityLevel.NORMAL)
    @Story("Регистрация → Валидный email")
    @ValueSource(strings = {
            "mail.ru",
            "gmail.com",
            "yandex.ru",
            "rambler.ru",
            "outlook.com"
    })
    void registrationWithValidDomainShouldSucceed(String domain) {
// 1) Сгенерировали и запомнили имя/фамилию
        String firstName = TestData.randomFirstName();
        String lastName  = TestData.randomLastName();

        // 2) Из тех же самых имён собираем email, транслитерируя в ASCII
        String email = TestData.toAsciiLatin(TestData.duplicateRandomFirstName()).toLowerCase(Locale.ROOT)
                + "." + TestData.toAsciiLatin(TestData.duplicateRandomLastName()).toLowerCase(Locale.ROOT)
                + "@" + domain;

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.clickOnRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitUntilOpened();
        registrationPage.registerNewUser(
                firstName,
                lastName,
                email,
                "user_" + System.nanoTime(),          // login
                TestData.randomPassword(),
                TestData.confirmRandomPassword()
        );

        Allure.step("Проверка успеха регистрации", () -> {
            assertThat(registrationPage.getRegistrationErrorTextOrNull())
                    .as("Регистрация с доменом %s должна завершиться без ошибок", domain)
                    .isNull();
        });
    }
}