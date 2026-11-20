package api.bankLoans;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import common.api.steps.BankSteps;
import common.api.support.BankWireMock;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import static java.net.HttpURLConnection.*;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Bank")
public class GETapiBankLoanProductsStandardTest {

    @RegisterExtension
    static WireMockExtension wm = BankWireMock.stub("standard_loan_product-success");

    @Test
    @DisplayName("GET /api/bank/loan-products/standard Получить стандартный кредитный продукт")
    void getStandardLoanProduct() {
        Response productResponse = BankSteps.getStandardLoanProduct();
        Allure.step("Проверить статус код ответа", () -> {
            assertThat(productResponse.statusCode()).isEqualTo(HTTP_OK);
        });
    }
}
