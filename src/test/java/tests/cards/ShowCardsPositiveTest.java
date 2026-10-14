package tests.cards;

import config.Config;
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
        assertThat(expectedCode).as("Код подтверждения введён корректно").isEqualTo(actualCode);
        bankLoansPage.submitOtp();
        bankLoansPage.waitUntilOtpFieldDisappears();
    }
}
