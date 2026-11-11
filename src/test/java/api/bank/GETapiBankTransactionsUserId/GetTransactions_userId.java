package api.bank.GETapiBankTransactionsUserId;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.models.responses.bank.BankTransfer;
import common.api.steps.BankSteps;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;


@Feature("Bank")
public class GetTransactions_userId {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    // Указываем путь ТОЛЬКО к папке с успешными сценариями логина
                    .usingFilesUnderClasspath("scenarios/transactions-success")
            )
            .build();

    @Test
    @DisplayName("GET /api/bank/transactions/{userId} — возвращает список пользователей")
    void getTransactions_userId() {
      //  String userId = TestConfig.INSTANCE.bankUserId();   // или просто "test-user-id"

        String userId = "Lorem";
        Response response = BankSteps.getTransactions(userId);
        assertThat(response.statusCode()).isEqualTo(200);
        List<BankTransfer> transactions = response.jsonPath().getList("", BankTransfer.class);
        assertThat(transactions).isNotEmpty();
        assertThat(transactions).allSatisfy(t -> { assertThat(t.getId()).isNotBlank();
            assertThat(t.getType()).isNotBlank(); assertThat(t.getAmount()).isNotNull(); });
    }
}
