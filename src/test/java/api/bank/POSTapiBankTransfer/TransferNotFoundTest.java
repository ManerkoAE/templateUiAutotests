package api.bank.POSTapiBankTransfer;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.steps.BankSteps;
import common.api.support.BankWireMock;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static java.net.HttpURLConnection.*;
import static common.utils.TestData.randomAmount;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bank")
@Feature("User")
@Story("Перевод не найден")
@Tag("smoke")
public class TransferNotFoundTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("transfer-not-found");

    @Tag("12")
    @Test
    @DisplayName("Перевод не найден: {0} → 404")
    void AddNewTransferSuccess() {

        String fromUserId = "testuset123";
        String recipient = "testuser456";
        Double amount = randomAmount();
        boolean isAccountNumber = true;
        Response response = BankSteps.addNewTransfer(
                fromUserId,
                recipient,
                amount,
                isAccountNumber);

        Allure.step("Проверить, что искомый перевод не найден(404)", () -> {
            assertThat(response.statusCode())
                    .withFailMessage("Перевод не найден. Ожидали %d, а получили %d.",
                            HTTP_OK, response.statusCode())
                    .isEqualTo(HTTP_NOT_FOUND);
        });
    }
}
