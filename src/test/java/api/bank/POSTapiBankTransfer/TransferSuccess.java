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
public class TransferSuccess {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    .usingFilesUnderClasspath("scenarios/transfer-success")
                    .globalTemplating(true)
            )
            .build();

    @Test
    @DisplayName("Создание нового трансфера: {0} → 200/201")
    void AddNewTransferSuccess() {
        String fromUserId = "adipisicing labore occaecat consectetur";
        String recipient = "nostrud anim esse";
        Double amount= randomAmount();
        boolean isAccountNumber = true;
        Response response = BankSteps.AddNewTransfer(
                fromUserId,
                recipient,
                amount,
                isAccountNumber
        );
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
