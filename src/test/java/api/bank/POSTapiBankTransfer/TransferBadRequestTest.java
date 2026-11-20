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
@Story("Проверка некорректного перевода")
@Tag("smoke")
@Tag("11")
public class TransferBadRequestTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("transfer-bad-request");

    @Test
    @DisplayName("Некорректные данные перевода: {0} → 400")
    void AddNewTransferFail() {

        String fromUserId = "testuset123";
        String wrongRecipient = "testuser456";
        Double amount = randomAmount();
        boolean isAccountNumber = true;
        Response response = BankSteps.addNewTransfer(
                fromUserId,
                wrongRecipient,
                amount,
                isAccountNumber);

        Allure.step("Проверить, что сервер вернул 400 Bad Request при некорректном получателе", () -> {
            assertThat(response.statusCode())
                    .withFailMessage("Некорректный статус-код при переводе на получателя '%s'. Ожидали %d, а получили %d. Тело ответа: %s",
                            wrongRecipient,
                            HTTP_BAD_REQUEST,
                            response.statusCode(),
                            response.asString())
                    .isEqualTo(HTTP_BAD_REQUEST);
        });
    }
}


