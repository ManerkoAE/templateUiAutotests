package api.bank.GETapiBankUserId;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.models.responses.bank.BankUser;
import common.api.steps.BankSteps;
import config.TestConfig;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Bank")
public class TakeUserId404 {
    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    // Указываем путь ТОЛЬКО к папке с успешными сценариями логина
                    .usingFilesUnderClasspath("scenarios/user-not-found")
            )
            .build();

    @Test
    @DisplayName("GET /user/{id} возвращает 404 для несуществующего id")
    void getUserByIdReturnsSameUserAsLogin() {

        Response loginResponse = BankSteps.login(TestConfig.INSTANCE.bankLogin(), TestConfig.INSTANCE.bankPassword());
        BankUser fromLogin = loginResponse.as(BankUser.class);
String wrongId="dfdfdf";
        Response userResponse = BankSteps.getUserById(wrongId);

        assertThat(userResponse.statusCode()).isEqualTo(404);
        assertThat(userResponse.jsonPath().getString("error")).isEqualTo("Пользователь не найден");
    }
}
