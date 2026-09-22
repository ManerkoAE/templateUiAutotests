package tests.bank_loans.positive;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.base.BaseBankAuthTest;

import java.time.Duration;

import static constants.Constant.Urls.BANK_LOANS_URL;

public class BankLoansPagePositiveTest extends BaseBankAuthTest {

    @Test
    public void bank_loans_page_Test() {
        // Логин уже выполнен в @BeforeEach родительского класса
        // Теперь можно перейти на страницу кредитов
        basePage.goToUrl(BANK_LOANS_URL);

        // Добавь свои проверки для страницы кредитов
        System.out.println("✅ Тест страницы кредитов выполнен");
        System.out.println("📍 Текущий URL: " + driver.getCurrentUrl());
        // 1. Ждём загрузки страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 2. Ждём, пока кнопка появится
        WebElement CheckCards = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[@data-testid='toggle-card-details']")
        ));
        // 3. Кликаем по кнопке
        CheckCards.click();
        System.out.println("✅ Осуществлен переход на страницу Мои карты");
        // 4. Дополнительная проверка - ждём появления кода
        try {
            WebElement codeField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("[data-testid='otp-code-value']")
            ));
            System.out.println("Код подтверждения: " + codeField.getText());
        } catch (Exception e) {
            System.out.println("⚠️ Код подтверждения не найден");
        }
        WebElement copyCode = driver.findElement(By.xpath("//button[@title ='Копироватьm код']"));
        copyCode.click();
        System.out.println("Код скопирован");
        // 5. Вводим код в поле ввода
        WebElement codeField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='otp-code-value']")));
        String expectedCode = codeField.getText();
        WebElement inputField = driver.findElement(By.xpath("//input[@data-testid='otp-input']"));
        inputField.clear();
        inputField.sendKeys(expectedCode);
        // Проверяем
        String actualCode = inputField.getAttribute("value");
// Сравниваем
        if (expectedCode.equals(actualCode)) {
            System.out.println("✅ Код подтверждения введен корректно: a" + actualCode);
            // Нажимаем подтверждение
            WebElement Acceptbutton = driver.findElement(By.xpath("//button[@data-testid='otp-submit-btn']"));
            Acceptbutton.click();
        } else {
            System.out.println("❌ Некорректный ввод кода!");
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@data-testid='otp-submit-btn']")));
        System.out.println("Код подтвержден");
        System.out.println("✅ Тест успешно завершен!");
    }
    }
