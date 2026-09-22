package tests.base;

import common.common.ConfigLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseBankAuthTest extends BaseTest {

    protected String login;
    protected String password;
    protected String baseUrl;
    protected WebDriverWait wait;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();

        login = ConfigLoader.get("Banklogin");
        password = ConfigLoader.get("Bankpassword");
        baseUrl = ConfigLoader.get("baseBankurl");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("🔐 Выполняем авторизацию перед тестом...");
        System.out.println("👤 Логин: " + login);
        System.out.println("🔗 URL: " + baseUrl);

        performLogin();

        System.out.println("✅ Авторизация выполнена успешно");
    }

    private void performLogin()  {
        // Открываем страницу логина
        driver.get(baseUrl + "/login");
        // Ждём загрузки страницы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form")));
        // Поле логина - скорее всего name="username" или id="userName"
        driver.findElement(By.id("username")).sendKeys(login);
        // Поле пароля - скорее всего name="password" или id="password"
        driver.findElement(By.id("password")).sendKeys(password);
        // Кнопка входа - ищем по тексту
        driver.findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        // Ждём, что перекинет на главную
        wait.until(ExpectedConditions.urlContains("/bank"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}