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

import static common.utils.TestData.randomAmount;
import static java.net.HttpURLConnection.*;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bank")
@Feature("User")
@Story("Создание новой операции перевода средств")
@Tag("smoke")
@Tag("7")
public class TransferSuccessTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("transfer-success");

    @Test
    @DisplayName("Создание нового трансфера: {0} → 200/201")
    void addNewTransferSuccess() {

        String fromUserId = "adipisicing labore occaecat consectetur";
        String recipient = "nostrud anim esse";
        Double amount = randomAmount();
        boolean isAccountNumber = true;
        Response response = BankSteps.addNewTransfer(
                fromUserId,
                recipient,
                amount,
                isAccountNumber);

        Allure.step("Проверить создание новой операции перевода средств", () -> {
            assertThat(response.statusCode())
                    .withFailMessage("Операция не выполнена. Ожидали %d, а получили %d", response.getStatusCode(), HTTP_BAD_REQUEST)
                    .isEqualTo(HTTP_OK);
        });
    }
}
