package tests.bank_loans.positive;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.base.BaseBankAuthTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankLoginPositiveTest extends BaseBankAuthTest {

    @Test
    void successfulLoginTest() {
        // Логин уже выполнен в @BeforeEach родительского класса
        // Проверяем, что мы на дашборде
        String expectedUrl = baseUrl;

        // Ждём, пока URL станет дашбордом
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        assertEquals(expectedUrl, driver.getCurrentUrl(),
                "Должен быть редирект на страницу банка");

        System.out.println("✅ Тест логина пройден!");
    }
}