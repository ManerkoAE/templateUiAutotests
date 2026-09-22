package tests.bank_loans.negative;

import common.common.ConfigLoader;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.base.BaseTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankLoginNegativeTest extends BaseTest {

    @Test
    void loginWithWrongPasswordShowsError() {
        String baseUrl = ConfigLoader.get("baseBankurl");
        String wrongLogin = ConfigLoader.get("BankWronglogin");     // ← загружаем
        String wrongPassword = ConfigLoader.get("BankWrongpassword"); // ← загружаем

        driver.get(baseUrl + "/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(wrongLogin);
        driver.findElement(By.id("password")).sendKeys(wrongPassword);
        driver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();

        String expectedError = "Неверные учетные данные";
        String actualError = driver.findElement(
                By.cssSelector("[data-testid='login-error']")
        ).getText();

        assertEquals(expectedError, actualError, "Должно быть сообщение об ошибке");
    }
}