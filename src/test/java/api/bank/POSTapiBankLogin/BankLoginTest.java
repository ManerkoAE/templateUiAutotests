package api.bank.POSTapiBankLogin;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import common.api.models.responses.bank.BankUser;
import common.api.steps.BankSteps;
import config.TestConfig;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Bank")
class BankLoginTest {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    // Указываем путь ТОЛЬКО к папке с успешными сценариями логина
                    .usingFilesUnderClasspath("scenarios/login-success")
            )
            .build();

    private final BankSteps bankSteps = new BankSteps();

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

         assertThat(response.getStatusCode()).isEqualTo(200);
        BankUser bankUser = response.as(BankUser.class);


        assertThat(bankUser.getEmail()).isEqualTo("test@qabank.com");
        assertThat(bankUser.getBalance()).isEqualTo(1000000);

        assertThat(bankUser)
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(expected);


    }

}
