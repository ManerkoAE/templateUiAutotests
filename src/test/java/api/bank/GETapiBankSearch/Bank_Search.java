package api.bank.GETapiBankSearch;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import common.api.models.responses.bank.BankUser;
import common.api.steps.BankSteps;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Feature("Bank")
public class Bank_Search {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    // Указываем путь ТОЛЬКО к папке с успешными сценариями логина
                    .usingFilesUnderClasspath("scenarios/search-success")
            )
            .build();

    @Test
    @DisplayName("GET /api/bank/search — возвращает список пользователей")
    void search(){
        Response response = BankSteps.getSearchResults("testuser");

        assertThat(response.getStatusCode()).isEqualTo(200);

        List<BankUser> users = response.jsonPath().getList("", BankUser.class);
        assertThat(users).hasSize(8);

        users.forEach(user -> {
            assertThat(user.getId()).isNotBlank();
            assertThat(user.getUsername()).isNotBlank();
            assertThat(user.getEmail()).contains("@");
            assertThat(user.getAccountNumber()).isNotBlank();
        });
        JsonNode root = response.as(JsonNode.class);
        assertThat(root.isArray()).isTrue();
        assertThat(root.get(0).has("createdAt")).isTrue();
         }
}
