package tests;

import common.pages.LoginPage;
import common.utils.SeleniumUtils;
import config.Config;
import org.junit.jupiter.api.BeforeEach;

public class BaseAuthTest extends BaseTest {
    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginAs(Config.INSTANCE.bankLogin(), Config.INSTANCE.bankPassword());
        SeleniumUtils.waitForUrlContains(driver, Config.INSTANCE.baseBankUrl());
    }
}