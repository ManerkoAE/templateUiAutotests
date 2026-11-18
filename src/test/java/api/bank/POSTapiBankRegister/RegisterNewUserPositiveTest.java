package api.bank.POSTapiBankRegister;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.steps.BankSteps;
import common.api.support.BankWireMock;
import common.utils.TestData;
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
@Story("Регистрация нового пользователя")

@Tag("smoke")
@Tag("2")
class RegisterNewUserPositiveTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("register-success");

    @Test
    @DisplayName("Регистрация нового пользователя → 200/201")
    void registerWithValidCredentials() {

        String username = TestData.randomLogin();
        String password = TestData.randomPassword();
        String firstName = TestData.randomFirstName();
        String lastName = TestData.randomLastName();
        String email = TestData.toAsciiLatin(TestData.duplicateRandomFirstName()).toLowerCase(Locale.ROOT)
                + "." + TestData.toAsciiLatin(TestData.duplicateRandomLastName()).toLowerCase(Locale.ROOT)
                + "@mail.ru";

        Response response = BankSteps.registerNewUser(username, password, firstName, lastName, email);
        Allure.step("Проверить регистрацию нового пользователя", () -> {
            assertThat(response.getStatusCode())
                    .withFailMessage("Введены некорректные данные. Ожидали %d, а получили %d", response.getStatusCode(), HTTP_BAD_REQUEST)
                    .isEqualTo(HTTP_OK);
        });
    }
}