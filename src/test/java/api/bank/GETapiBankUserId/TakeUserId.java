package api.bank.GETapiBankUserId;

import common.api.models.responses.bank.BankUser;
import common.api.steps.BankSteps;
import config.TestConfig;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


import static org.assertj.core.api.Assertions.assertThat;

@Feature("Bank")
public class TakeUserId {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    .usingFilesUnderClasspath("scenarios/user-success")
            )
            .build();
    @Test
    @DisplayName("GET /user/{id} возвращает того же пользователя, что и login")
    void getUserByIdReturnsSameUserAsLogin() {

        Response loginResponse = BankSteps.login(TestConfig.INSTANCE.bankLogin(), TestConfig.INSTANCE.bankPassword());
        BankUser fromLogin = loginResponse.as(BankUser.class);

        Response userResponse = BankSteps.getUserById(fromLogin.getId());

        assertThat(userResponse.statusCode()).isEqualTo(200);

        BankUser fromGet = userResponse.as(BankUser.class);

        assertThat(fromGet)
                .usingRecursiveComparison()
                .ignoringFields("createdAt")
                .isEqualTo(fromLogin);
    }
}
