package tests.login;

import common.pages.LoginPage;
import common.utils.TestData;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import static config.Constant.ExpectedMessages.WRONG_CREDENTIALS_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginNegativeTest extends BaseTest {

    @Test
    void loginWithInvalidCredentialsShowsError() {

        String wrongLogin = TestData.randomLogin();
        String wrongPassword = TestData.randomPassword();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.loginAs(wrongLogin, wrongPassword);
assertThat(loginPage.getErrorText()).as("Должно быть сообщение об ошибке").
        isEqualTo(WRONG_CREDENTIALS_ERROR);

    }
}