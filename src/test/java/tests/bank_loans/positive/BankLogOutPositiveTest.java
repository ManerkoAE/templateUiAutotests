package tests.bank_loans.positive;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankLogOutPositiveTest extends BankLoginPositiveTest {
@Test
    public void setLogOutButton() {
        boolean IsLoggedIn = basePage.isElementPresent(By.cssSelector("[title='test@qabank.com']"));
        assertTrue(IsLoggedIn, "Пользователь авторизован");
        WebElement logOutButton = driver.findElement(By.cssSelector("[data-testid='logout-btn']"));
        logOutButton.click();
        driver.get("https://demoqa.ru/bank/login");
    System.out.println("Пользователь не авторизован");
    }


}
