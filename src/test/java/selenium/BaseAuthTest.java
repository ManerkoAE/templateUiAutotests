package selenium;

import common.selenium.pages.BankPage;
import common.selenium.pages.LoginPage;
import config.TestConfig;
import org.junit.jupiter.api.BeforeEach;

public class BaseAuthTest extends BaseTest {
    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginAs(TestConfig.INSTANCE.bankLogin(), TestConfig.INSTANCE.bankPassword());

        new BankPage(driver).waitUntilOpened();
    }
}
