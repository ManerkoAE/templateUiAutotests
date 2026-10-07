package tests.Login;

import config.ConfigLoader;
import common.pages.LoginPage;
import org.junit.jupiter.api.Test;
import common.core.BaseTest;

import static config.Constant.ExpectedMessages.WRONG_CREDENTIALS_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginNegativeTest extends BaseTest {

    @Test
    void loginWithInvalidCredentialsShowsError() {
        String baseUrl = ConfigLoader.get("BaseBankUrl");
        String wrongLogin = ConfigLoader.get("BankWrongLogin");
        String wrongPassword = ConfigLoader.get("BankWrongPassword");

        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseUrl + "/login");
        loginPage.loginAs(wrongLogin, wrongPassword);

        assertEquals(WRONG_CREDENTIALS_ERROR, loginPage.getErrorText(),
                "Должно быть сообщение об ошибке");
    }
}