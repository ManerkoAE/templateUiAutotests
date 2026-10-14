package common.pages;

import config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Путь страницы относительно baseUrl. По умолчанию — корень.
     */
    protected String path() {
        return "";
    }

    @Step("Открыть страницу ")
    public void open() {
        String url = Config.INSTANCE.baseBankUrl() + path();
        driver.get(url);
    }

    @Step("Получить актуальную страницу сайта")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}