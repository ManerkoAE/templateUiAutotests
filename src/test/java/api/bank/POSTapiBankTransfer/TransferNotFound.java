package api.bank.POSTapiBankTransfer;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.steps.BankSteps;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static common.utils.TestData.randomAmount;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Bank")
public class TransferNotFound {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    .usingFilesUnderClasspath("scenarios/transfer-not-found")
                    .globalTemplating(true)
            )
            .build();

    @Test
    @DisplayName("Перевод не найден: {0} → 404")
    void AddNewTransferSuccess() {
        String fromUserId = "testuset123";
        String recipient = "testuser456";
        Double amount = randomAmount();
        boolean isAccountNumber = true;
        Response response = BankSteps.AddNewTransfer(
                fromUserId,
                recipient,
                amount,
                isAccountNumber
        );
        assertThat(response.statusCode()).isEqualTo(404);
    }
}
