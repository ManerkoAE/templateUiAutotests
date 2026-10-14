package tests.cards;

import config.Config;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.BaseAuthTest;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ShowCardsPositiveTest extends BaseAuthTest {

    @Test
    public void ShowCards() {
        driver.get(Config.INSTANCE.baseBankUrl());
        log.info("Текущий URL: {} ", Config.INSTANCE.baseBankUrl());
        bankLoansPage.clickShowCardDetails();
        log.info(" Осуществлен переход на страницу Мои карты");

        String expectedCode = bankLoansPage.getOtpCode();
        log.info("Код подтверждения: {} ", expectedCode);

        bankLoansPage.clickCopyCode();
        log.info("Код скопирован");

        bankLoansPage.enterOtpCode(expectedCode);
        String actualCode = bankLoansPage.getOtpInputValue();
        assertThat(expectedCode).as("Код подтверждения введён корректно").isEqualTo(actualCode);
        bankLoansPage.submitOtp();
        bankLoansPage.waitUntilOtpFieldDisappears();
        log.info("Код подтвержден");

        log.info("Тест успешно завершен!");
    }
}
