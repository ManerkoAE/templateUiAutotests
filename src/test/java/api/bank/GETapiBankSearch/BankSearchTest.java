package api.bank.GETapiBankSearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.support.BankWireMock;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.RegisterExtension;
import common.api.models.responses.bank.BankUser;
import common.api.steps.BankSteps;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.net.HttpURLConnection.*;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bank")
@Feature("Search")
@Story("Проверка работы поиска клиентов банка")

@Tag("smoke")
@Tag("5")
public class BankSearchTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("search-success");

    @Test
    @DisplayName("GET /api/bank/search — возвращает список пользователей")
    void search() {
        Response response = BankSteps.getSearchResults("testuser");

        SoftAssertions.assertSoftly(softly -> {
            Allure.step("Проверить возвращение списка пользователей", () -> {
                softly.assertThat(response.getStatusCode())
                        .withFailMessage("Пользователь не найден. Ожидали %d, а получили %d", response.getStatusCode(), HTTP_NOT_FOUND)
                        .isEqualTo(HTTP_OK);
            });

            List<BankUser> users = response.jsonPath().getList("", BankUser.class);
            Allure.step("Проверить отображение данных пользователя", () -> {
                softly.assertThat(users).hasSize(8);
                users.forEach(user -> {
                    softly.assertThat(user.getId()).isNotBlank();
                    softly.assertThat(user.getUsername()).isNotBlank();
                    softly.assertThat(user.getEmail()).contains("@");
                    softly.assertThat(user.getAccountNumber()).isNotBlank();
                });
                JsonNode root = response.as(JsonNode.class);
                softly.assertThat(root.isArray()).isTrue();
                softly.assertThat(root.get(0).has("createdAt")).isTrue();
            });
        });
    }
}
