package common.pages;

import config.Config;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Slf4j
public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /** Путь страницы относительно baseUrl. По умолчанию — корень. */
    protected String path() {
        return "";
    }

    @Step("Открыть страницу ")
    public void open() {
        String url = Config.INSTANCE.baseBankUrl() + path();
        log.info("Opening URL: {}", url);
        driver.get(url);
    }

    public void goToUrl(String url) {
        driver.get(url);

    }

    @Step("Получить актуальную страницу сайта")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

   }