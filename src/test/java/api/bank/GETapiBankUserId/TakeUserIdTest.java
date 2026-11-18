package api.bank.GETapiBankUserId;

import common.api.models.responses.bank.BankUser;
import common.api.steps.BankSteps;
import common.api.support.BankWireMock;
import config.TestConfig;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;


import static java.net.HttpURLConnection.*;

@Epic("Bank")
@Feature("User")
@Tag("smoke")
@Tag("4")
@Story("Проверка наличия пользователя по ID")
public class TakeUserIdTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("user-success");

    @Test
    @DisplayName("GET /user/{id} возвращает того же пользователя, что и login")
    void getUserByIdReturnsSameUserAsLogin() {

        Response loginResponse = BankSteps.login(TestConfig.INSTANCE.bankLogin(), TestConfig.INSTANCE.bankPassword());
        BankUser fromLogin = loginResponse.as(BankUser.class);
        Response userResponse = BankSteps.getUserById(fromLogin.getId());
        BankUser fromGet = userResponse.as(BankUser.class);
        SoftAssertions.assertSoftly(softly -> {
            Allure.step("Проверить наличие пользователя по ID", () -> {
                softly.assertThat(userResponse.statusCode())
                        .withFailMessage("Некорректно введенны данные. Ожидали  %d, а получили %d ", userResponse.statusCode(), HTTP_BAD_REQUEST)
                        .isEqualTo(HTTP_OK);
               softly. assertThat(fromGet)
                        .usingRecursiveComparison()
                        .ignoringFields("createdAt")
                        .isEqualTo(fromLogin);
            });
        });
    }
}
