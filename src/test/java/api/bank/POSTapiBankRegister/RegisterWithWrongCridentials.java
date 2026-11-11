package api.bank.POSTapiBankRegister;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.steps.BankSteps;
import common.utils.TestData;
import config.TestConfig;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Locale;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Bank")
public class RegisterWithWrongCridentials {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    // Указываем путь ТОЛЬКО к папке с успешными сценариями логина
                    .usingFilesUnderClasspath("scenarios/register-bad-request")
            )
            .build();
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


        assertThat(response.getStatusCode()).isEqualTo(400);

    }
}
