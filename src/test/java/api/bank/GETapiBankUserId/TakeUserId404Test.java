package api.bank.GETapiBankUserId;

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
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bank")
@Feature("User")
@Story("Проверка наличия пользователя по ID (Пользователь не найден - 404)")
@Tag("smoke")
@Tag("10")
public class TakeUserId404Test {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("user-not-found");

    @Test
    @DisplayName("GET /user/{id} возвращает 404 для несуществующего id")
    void getUserByIdReturnsSameUserAsLogin() {

        Response loginResponse = BankSteps.login(TestConfig.INSTANCE.bankLogin(), TestConfig.INSTANCE.bankPassword());
        BankUser fromLogin = loginResponse.as(BankUser.class);
        String wrongId = "dfdfdf";
        Response userResponse = BankSteps.getUserById(wrongId);
        Allure.step("Проверить наличие пользователя по ID. Пользователь не найден(404)", () -> {
            assertThat(userResponse.statusCode())
                    .withFailMessage("Пользователь не найден. Ожидали %s, получили %s", fromLogin.getId(), wrongId)
                    .isEqualTo(HTTP_NOT_FOUND);
            SoftAssertions.assertSoftly(softly -> {
                softly.assertThat(userResponse.jsonPath().getString("error")).isEqualTo("Пользователь не найден");
            });
        });
    }
}
