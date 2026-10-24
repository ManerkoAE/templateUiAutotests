package common.pages;

import common.utils.SeleniumUtils;
import config.TestConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.regex.Pattern;


public abstract class BasePage {

    private static final String ALLOWED_URL_TAIL = "/?(?:[?#].*)?$";

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = Objects.requireNonNull(driver, "driver не может быть null");
    }

    protected abstract PagePath path();

    public String url() {
        String baseUrl = TestConfig.INSTANCE.baseBankUrl();
        String normalizedBaseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        return normalizedBaseUrl + path().value();
    }

    public void open() {
        String url = url();
        Allure.step("Открыть страницу " + url, () -> driver.get(url));
    }

    @Step("Дождаться открытия страницы")
    public void waitUntilOpened() {
        SeleniumUtils.waitForUrlMatches(driver, urlPattern());
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    private String urlPattern() {
        return Pattern.quote(url()) + ALLOWED_URL_TAIL;
    }
}
