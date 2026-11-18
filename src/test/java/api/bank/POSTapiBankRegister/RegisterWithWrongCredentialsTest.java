package api.bank.POSTapiBankRegister;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.steps.BankSteps;
import common.api.support.BankWireMock;
import common.utils.TestData;
import config.TestConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Locale;

import static java.net.HttpURLConnection.*;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bank")
@Feature("User")
@Story("Проверка ввода некорректных(существующих) данных при регистрации")

@Tag("smoke")
@Tag("8")
public class RegisterWithWrongCredentialsTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("register-bad-request");

    @Test
    @DisplayName("Негативный: {0} → 400/401 -> Имя пользователя занято")
    void registerWithInValidCredentials() {

        String username = TestConfig.INSTANCE.bankLogin();
        String password = TestData.randomPassword();
        String firstName = TestData.randomFirstName();
        String lastName = TestData.randomLastName();
        String email = TestData.toAsciiLatin(TestData.duplicateRandomFirstName()).toLowerCase(Locale.ROOT)
                + "." + TestData.toAsciiLatin(TestData.duplicateRandomLastName()).toLowerCase(Locale.ROOT)
                + "@mail.ru";

        Response response = BankSteps.registerNewUser(username, password, firstName, lastName, email);

        Allure.step("Проверить валидность ввода данных нового пользователя", () -> {
            assertThat(response.getStatusCode())
                    .withFailMessage("Некорректно введены данные. Пользователь с такими данными уже существует. Ожидали %d, а получили %d", response.getStatusCode(), HTTP_OK)
                    .isEqualTo(HTTP_BAD_REQUEST);
        });

    }
}
