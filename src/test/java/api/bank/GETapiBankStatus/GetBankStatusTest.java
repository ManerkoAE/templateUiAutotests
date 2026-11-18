package api.bank.GETapiBankStatus;

import common.api.steps.ApiBaseTest;
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
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import static java.net.HttpURLConnection.*;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Bank")
@Feature("Status")
@Story("Проверка статуса работы системы банка")

@Tag("smoke")
@Tag("1")
public class GetBankStatusTest extends ApiBaseTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("status-success");

    @Test
    @DisplayName("GET /api/bank/status Получить статус системы банка")
    void getBankStatus() {
        Response bankResponse = BankSteps.getBankStatus();
        Allure.step("Проверить статус код ответа", () -> {
            assertThat(bankResponse.statusCode())
                    .withFailMessage("Система недоступна. Ожидали %d, а получили %d", bankResponse.getStatusCode(), HTTP_BAD_REQUEST)
                    .isEqualTo(HTTP_OK)
            ;
        });
    }
}
