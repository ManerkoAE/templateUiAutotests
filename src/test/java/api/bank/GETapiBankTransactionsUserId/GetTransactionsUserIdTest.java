package api.bank.GETapiBankTransactionsUserId;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.models.responses.bank.BankTransfer;
import common.api.steps.BankSteps;
import common.api.support.BankWireMock;
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

import java.util.List;

import static java.net.HttpURLConnection.*;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bank")
@Feature("User")
@Story("Проверка списка транзакций пользователя")
@Tag("smoke")
@Tag("6")
public class GetTransactionsUserIdTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("transactions-success");

    @Test
    @DisplayName("GET /api/bank/transactions/{userId} — возвращает список транзакций пользователя")
    void getTransactionsByUserId() {

        String userId = "Lorem";
        Response response = BankSteps.getTransactions(userId);

        Allure.step("Проверить список транзакций пользователя", () -> {
            assertThat(response.statusCode()).isEqualTo(HTTP_OK)
                    .withFailMessage("Пользователь с таким ID не существует");
        });

        List<BankTransfer> transactions = response.jsonPath().getList("", BankTransfer.class);

        SoftAssertions.assertSoftly(softly -> {
            Allure.step("Проверить наличие транзакций и их корректное отображение", () -> {
                softly.assertThat(transactions).isNotEmpty();
                softly.assertThat(transactions).allSatisfy(t -> {
                    softly.assertThat(t.getId()).isNotBlank();
                    softly.assertThat(t.getType()).isNotBlank();
                    softly.assertThat(t.getAmount()).isNotNull();
                });
            });
        });
    }
}
