package tests.cards;

import config.Config;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import tests.BaseAuthTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ShowCardsPositiveTest extends BaseAuthTest {

    @Test
    public void showCards() {
        driver.get(Config.INSTANCE.baseBankUrl());

        bankLoansPage.clickShowCardDetails();

        String expectedCode = bankLoansPage.getOtpCode();

        bankLoansPage.clickCopyCode();

        bankLoansPage.enterOtpCode(expectedCode);
        String actualCode = bankLoansPage.getOtpInputValue();
        Allure.step("Проверка корректности ввода кода", () -> {
            assertThat(expectedCode).isEqualTo(actualCode);
            bankLoansPage.submitOtp();
            bankLoansPage.waitUntilOtpFieldDisappears();
        });
    }
}
