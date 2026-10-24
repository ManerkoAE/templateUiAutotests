package tests.cards;

import common.pages.BankPage;
import common.pages.bank.CardsPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.BaseAuthTest;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Отображение карт после OTP")
public class ShowCardsNumberPositiveTest extends BaseAuthTest {

    @Test
    @Description("Проверка отображения карт после прохождения OTP-верификации")
    @Severity(SeverityLevel.NORMAL)
    public void showCards() {

        BankPage bankPage = new BankPage(driver);
        bankPage.clickShowCardDetails();

        CardsPage cardsPage = new CardsPage(driver);

        String expectedCode = cardsPage.getOtpCode();

        cardsPage.clickCopyCode();

        cardsPage.enterOtpCode(expectedCode);
        String actualCode = cardsPage.getOtpInputValue();
        Allure.step("Проверка корректности ввода кода", () -> {
            assertThat(expectedCode).isEqualTo(actualCode);
            cardsPage.submitOtp();
            cardsPage.waitUntilOtpFieldDisappears();
        });
    }
}