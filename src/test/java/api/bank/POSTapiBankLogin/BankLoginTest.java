package api.bank.POSTapiBankLogin;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.models.responses.bank.BankUser;
import common.api.steps.BankSteps;
import common.api.support.BankWireMock;
import config.TestConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static java.net.HttpURLConnection.*;

@Epic("Bank")
@Feature("User")
@Tag("smoke")
@Story("Проверка авторизации пользователя и корректности отображенния его данных")
class BankLoginTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("login-success");

    private final BankSteps bankSteps = new BankSteps();

    @Tag("3")
    @Test
    @DisplayName("Логин в банк возвращает 200 OK")
    void loginReturns200() {

        String username = TestConfig.INSTANCE.bankLogin();
        String password = TestConfig.INSTANCE.bankPassword();
        BankUser expected = BankUser.builder()
                .id("test-user-id")
                .username("testuser")
                .firstName("Test")
                .lastName("User")
                .email("test@qabank.com")
                .balance(1000000.0)
                .accountNumber("220012345678")
                .build();

        Response response = bankSteps.login(username, password);
        BankUser bankUser = response.as(BankUser.class);

        SoftAssertions.assertSoftly(softly -> {

            Allure.step("Проверить успешную авторизацию пользователя", () -> {
                softly.assertThat(response.getStatusCode())
                        .withFailMessage("Данные пользователя не валидные. Ожидали %d, получили %d", response.getStatusCode(), HTTP_BAD_REQUEST)
                        .isEqualTo(HTTP_OK);
            });

            Allure.step("Проверить соответствие почты", () -> {
                softly.assertThat(bankUser.getEmail())
                        .withFailMessage("Почта не совпадает. Ожидали %s, а получили %s", bankUser.getEmail(), "test@qabank1.com")
                        .isEqualTo("test@qabank.com");
            });

            Allure.step("Проверить соответствие баланса", () -> {
                softly.assertThat(bankUser.getBalance())
                        .withFailMessage("Баланс не совпадает. Ожидали %.2f, а получили %.2f", bankUser.getBalance(), 20000001.0)
                        .isEqualTo(1000000.0);
            });

            Allure.step("Проверить коректное отображение всех данных пользователя", () -> {
                softly.assertThat(bankUser)
                        .as("Данные пользователя не совпадают. Ожидали %s , а получили %s  ", expected, bankUser)
                        .usingRecursiveComparison()
                        .ignoringFields("createdAt")
                        .isEqualTo(expected);
            });
        });
    }
}
