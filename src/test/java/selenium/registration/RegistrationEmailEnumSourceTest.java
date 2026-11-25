package selenium.registration;

import common.selenium.pages.LoginPage;
import common.selenium.pages.RegistrationPage;
import common.utils.TestData;
import config.InvalidEmail;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import selenium.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationEmailEnumSourceTest extends BaseTest {

    @ParameterizedTest(name = "Негативный тест: Неверный формат email {0}")
    @Description("Проверка, что при вводе email с неверным форматом показывается сообщение об ошибке")
    @Severity(SeverityLevel.NORMAL)
    @Story("Регистрация → Неправильный формат email")
    @EnumSource(InvalidEmail.class)

    void registrationShouldFailForInvalidEmailFormat(InvalidEmail errorType) {

        String email = emailFor(errorType);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.clickOnRegistrationButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitUntilOpened();

        registrationPage.registerNewUser(
                TestData.randomFirstName(),
                TestData.randomLastName(),
                email,
                "user_" + System.nanoTime(),
                TestData.randomPassword(),
                TestData.confirmRandomPassword()
        );

        Allure.step("Проверка сообщения об ошибке", () -> {
            String customMessage = registrationPage.getRegistrationErrorTextOrNull();
            String nativeMessage = registrationPage.getEmailValidationMessage();

            // При HTML5-валидации кастомный блок не появится — берём нативное сообщение браузера
            String actual = customMessage != null ? customMessage : nativeMessage;

            // 1) Кладём в Allure то, что реально видит пользователь
            Allure.addAttachment(
                    "Сообщение об ошибке для " + errorType,
                    "text/plain",
                    "Ожидали (по домену): '" + errorType.getExpectedMessage() + "'\n" +
                            "Серверное сообщение: " + (customMessage == null
                            ? "<не появилось — форма заблокирована HTML5>"
                            : "'" + customMessage + "'") + "\n" +
                            "Нативное сообщение браузера: '" + nativeMessage + "'"
            );

            // 2) Проверяем только то, что сообщение вообще есть (ловим регресс «валидация пропала»)
            assertThat(actual)
                    .as(() -> String.format(
                            "Ожидали сообщение для %s ('%s'), но ни серверное, ни нативное не появилось. " +
                                    "Похоже, приложение принимает невалидный email '%s'.",
                            errorType, errorType.getExpectedMessage(), email))
                    .isNotBlank();
        });
    }

    /** Email, соответствующий типу ошибки. */
    private static String emailFor(InvalidEmail type) {
        return switch (type) {
            case NO_AT_SYMBOL  -> "userdomain.com";
            case NO_DOMAIN    -> "user@";
            case DOUBLE_AT    -> "user@@domain.com";
            case SPACE_INSIDE -> "user name@domain.com";
        };
    }
}