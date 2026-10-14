package tests.registration;

import common.pages.RegistrationPage;
import common.utils.TestData;
import config.Config;
import org.junit.jupiter.api.Test;
import tests.BaseTest;
import static config.Constant.ExpectedMessages.WRONG_UserName_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

public class RegistrationNegativeTest extends BaseTest {

    @Test
    void RegistrationWithInvalidCredentialsShowsError() {

        String firstName = TestData.randomFirstName();
        String lastName = TestData.randomLastName();
        String email = TestData.randomEmail();
        String wrongLogin = "testuser";
        String newPassword = TestData.randomPassword();
        String confirmNewPassword = TestData.confirmRandomPassword();


        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.open();
        assertThat(registrationPage.getCurrentUrl())
                .as("Страница регистрации должна быть открыта")
                .isEqualTo(Config.INSTANCE.baseBankUrl() + "/register");

        registrationPage.registerNewUser(firstName, lastName,email,wrongLogin,newPassword,confirmNewPassword);
        assertThat(registrationPage.getRegistrationErrorText()).as("Должно быть сообщение об ошибке").
                isEqualTo(WRONG_UserName_ERROR);

    }
}
