package api.bank.POSTapiBankRegister;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.steps.BankSteps;
import common.utils.TestData;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Bank")
class RegisterNewUserPositiveTest {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    .usingFilesUnderClasspath("scenarios/register-success")
                    .globalTemplating(true)
            )
            .build();
    @Test
    @DisplayName("Позитивный: {0} → 200/201")
    void registerWithValidCredentials() {

        String username = TestData.randomLogin();
        String password = TestData.randomPassword();
        String firstName = TestData.randomFirstName();
        String lastName = TestData.randomLastName();
        String email = TestData.toAsciiLatin(TestData.duplicateRandomFirstName()).toLowerCase(Locale.ROOT)
                + "." + TestData.toAsciiLatin(TestData.duplicateRandomLastName()).toLowerCase(Locale.ROOT)
                + "@mail.ru";

        Response response = BankSteps.registerNewUser(username, password, firstName, lastName, email);


        assertThat(response.getStatusCode()).isEqualTo(200);
    }
}