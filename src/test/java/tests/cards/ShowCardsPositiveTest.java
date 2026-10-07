package tests.cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import common.core.BaseAuthTest;

import static config.Constant.Urls.BANK_LOANS_URL;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowCardsPositiveTest extends BaseAuthTest {

    @Test
    public void ShowCards() {
        basePage.goToUrl(BANK_LOANS_URL);
        LOGGER.info("Текущий URL: {} ", BANK_LOANS_URL);
        bankLoansPage.clickShowCardDetails();
        LOGGER.info(" Осуществлен переход на страницу Мои карты");

        String expectedCode = bankLoansPage.getOtpCode();
        LOGGER.info("Код подтверждения: {} ", expectedCode);

        bankLoansPage.clickCopyCode();
        LOGGER.info("Код скопирован");

        bankLoansPage.enterOtpCode(expectedCode);
        String actualCode = bankLoansPage.getOtpInputValue();

        Assertions.assertEquals(expectedCode, actualCode,
                "Код подтверждения введён корректно");

        bankLoansPage.submitOtp();
        bankLoansPage.waitUntilOtpFieldDisappears();
        LOGGER.info("Код подтвержден");

        LOGGER.info("Тест успешно завершен!");
    }
}
