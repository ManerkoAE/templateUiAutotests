package api.bank.GETapiBankStatus;

import common.api.steps.BankSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import static org.assertj.core.api.Assertions.assertThat;


public class GetStatus {

    @RegisterExtension
    static WireMockExtension wm = WireMockExtension.newInstance()
            .options(wireMockConfig()
                    .port(3002)
                    // Указываем путь ТОЛЬКО к папке с успешными сценариями логина
                    .usingFilesUnderClasspath("scenarios/status-success")
            )
            .build();
    @Test
    @DisplayName("GET /api/bank/status Получить статус системы банка")
    void getBankStatus() {
        Response bankResponse = BankSteps.getBankStatus();
        assertThat(bankResponse.statusCode()).isEqualTo(200);

    }

}
