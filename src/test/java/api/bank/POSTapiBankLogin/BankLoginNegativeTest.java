package api.bank.POSTapiBankLogin;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import common.api.steps.BankSteps;
import config.TestConfig;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@Feature("Bank")
class BankLoginNegativeTest {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    .usingFilesUnderClasspath("scenarios/login-unautorized")
            )
            .build();

    private final BankSteps bankSteps = new BankSteps();

    @Test
    @DisplayName("Логин в банк возвращает 401")
    void loginReturns401() {
        String username = TestConfig.INSTANCE.bankWrongLogin();
        String password = TestConfig.INSTANCE.bankWrongPassword();

        Response response = bankSteps.login(username, password);

        assertThat(response.getStatusCode())
                .isEqualTo(401);
    }
}
