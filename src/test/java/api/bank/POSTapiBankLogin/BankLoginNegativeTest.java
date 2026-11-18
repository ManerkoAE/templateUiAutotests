package api.bank.POSTapiBankLogin;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.support.BankWireMock;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.RegisterExtension;

import common.api.steps.BankSteps;
import config.TestConfig;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.*;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bank")
@Feature("User")
@Tag("smoke")
@Tag("9")
@Story("Пользователь не авторизован")
class BankLoginNegativeTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("login-unautorized");

    private final BankSteps bankSteps = new BankSteps();

    @Test
    @DisplayName("Логин в банк возвращает 401")
    void loginReturns401() {
        String username = TestConfig.INSTANCE.bankWrongLogin();
        String password = TestConfig.INSTANCE.bankWrongPassword();

        Response response = bankSteps.login(username, password);
        Allure.step("Проверить, авторизован ли пользователь", () -> {
            assertThat(response.getStatusCode())
                    .withFailMessage("Пользователь не авторизован. Ожидали %d, а получили %d", HTTP_OK, response.getStatusCode())
                    .isEqualTo(HTTP_UNAUTHORIZED);
        });

    }
}
