package tests.Login;

import config.ConfigLoader;
import common.pages.LoginPage;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import static config.Constant.ExpectedMessages.WRONG_CREDENTIALS_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginNegativeTest extends BaseTest {

    @Test
    void loginWithInvalidCredentialsShowsError() {
        String baseUrl = ConfigLoader.get("BaseBankUrl");
        String wrongLogin = ConfigLoader.get("BankWrongLogin");
        String wrongPassword = ConfigLoader.get("BankWrongPassword");

        LoginPage loginPage = new LoginPage(driver);
        driver.get(baseUrl + "/login");
        loginPage.loginAs(wrongLogin, wrongPassword);
assertThat(loginPage.getErrorText()).as("Должно быть сообщение об ошибке").
        isEqualTo(WRONG_CREDENTIALS_ERROR);

    }
}