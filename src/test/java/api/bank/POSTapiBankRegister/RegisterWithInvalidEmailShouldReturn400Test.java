package api.bank.POSTapiBankRegister;

import common.api.errors.InvalidEmailApiError;
import common.api.steps.BankSteps;
import io.qameta.allure.*;
import io.restassured.response.Response;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
@RequiredArgsConstructor
@Epic("Bank")
@Feature("User")
@Story("Параметризированный тест проверки ввода невалидной почты при регистрации")
@Tag("smoke")
@Tag("13")
@Disabled("DEF-1234: API принимает некорректный email (ожидался 400 INVALID_EMAIL). Баг заведён 2026-11-01")
@Link("https://jira.example.com/browse/DEF-1234")
@Name("name = DEF-1234")
@Tag("bug")
class RegisterWithInvalidEmailShouldReturn400Test {


    @ParameterizedTest(name = "Негативный: {0} → 400")
    @DisplayName("Ввод невалидной почты при регистрации")
    @EnumSource(InvalidEmailApiError.class)
    void registerWithInvalidEmailShouldReturn400(InvalidEmailApiError error) {

        Response response = BankSteps.registerWithWrongEmail(emailFor(error));

        Allure.addAttachment(
                "Тело ответа для " + error,
                "application/json",
                response.asPrettyString());

        assertThat(response.statusCode())
                .as("HTTP-код для %s", error)
                .isEqualTo(error.getStatus());

        assertThat(response.jsonPath().getString("error"))
                .as("Код ошибки для %s (тело: %s)", error, response.asPrettyString())
                .isEqualTo(error.getError());
    }

    private static String emailFor(InvalidEmailApiError error) {
        return switch (error) {
            case NO_AT_SYMBOL -> "userdomain.com";
            case NO_DOMAIN -> "user@";
            case DOUBLE_AT -> "user@@domain.com";
            case SPACE_INSIDE -> "user name@domain.com";
        };
    }
}