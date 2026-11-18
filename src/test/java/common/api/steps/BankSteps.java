package common.api.steps;

import common.api.endpoints.Endpoint;
import common.api.models.requests.bank.BankLoginRequest;
import common.api.models.requests.bank.BankRegisterRequest;
import common.api.models.requests.bank.BankTransferRequest;
import common.api.specs.ApiSpecs;
import common.utils.TestData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BankSteps {

    @Step("Вызов метода /login")
    public static Response login(String username, String password) {
        BankLoginRequest body = BankLoginRequest.builder()
                .username(username)
                .password(password)
                .build();

        return given()
                .spec(ApiSpecs.request())
                .body(body)
                .when()
                .post(Endpoint.LOGIN.path());
    }

    @Step("Вызов метода /user/{id}")
    public static Response getUserById(String id) {

        return given()
                .spec(ApiSpecs.request())
                .pathParam("id", id)
                .when()
                .get(Endpoint.USER.path())
                .then()
                .extract().response();
    }
    @Step("Регистрация нового пользователя {0}")
    public static Response registerNewUser(String username, String password, String firstName, String lastName, String email) {
        BankRegisterRequest body = BankRegisterRequest.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
        return given()
                .spec(ApiSpecs.request())
                .body(body)
                .when()
                .post(Endpoint.REGISTER.path())
                .then()
                .extract().response();
    }

    @Step("Регистрация с некорректным email: {0}")
    public static Response registerWithWrongEmail(String email) {
        BankRegisterRequest body = BankRegisterRequest.builder()
                .username(TestData.randomLogin())
                .password(TestData.randomPassword())
                .firstName(TestData.randomFirstName())
                .lastName(TestData.randomLastName())
                .email(email)
                .build();
        return given()
                .spec(ApiSpecs.request())
                .body(body)
                .when()
                .post(Endpoint.REGISTER.path())
                .then()
                .extract().response();
    }

    @Step("Вызов метода GET /api/bank/status")
    public static Response getBankStatus() {
        return given()
                .spec(ApiSpecs.request())
                .when()
                .get(Endpoint.STATUS.path())
                .then()
                .extract().response();
    }

    @Step("Вызов метода GET /api/bank/search?q={query}")
    public static Response getSearchResults(String query) {
        return given()
                .spec(ApiSpecs.request())
                .queryParam("q", query)
                .when()
                .get(Endpoint.SEARCH.path())
                .then()
                .extract().response();
    }
    @Step("Вызов метода GET /api/bank/transactions/{userId}")
    public static Response getTransactions(String userId) {
        return given()
                .spec(ApiSpecs.request())
                .pathParam("userId", userId)
                .when()
                .get(Endpoint.TRANSACTIONS.path())
                .then()
                .extract().response();
    }

    @Step("Создание нового перевода")
    public static Response addNewTransfer(String fromUserId, String recipient, Double amount, boolean isAccountNumber) {
        BankTransferRequest body = BankTransferRequest.builder()
                .fromUserId(fromUserId)
                .recipient(recipient)
                .amount(amount)
                .isAccountNumber(isAccountNumber)
                .build();
        return given()
                .spec(ApiSpecs.request())
                .body(body)
                .when()
                .post(Endpoint.TRANSFER.path())
                .then()
                .extract().response();
    }

    @Step("Вызов метода GET /api/bank/loan-products/standard")
    public static Response getStandardLoanProduct() {
        return given()
                .spec(ApiSpecs.request())
                .when()
                .get(Endpoint.STANDARD_LOAN_PRODUCT.path())
                .then()
                .extract().response();
    }
}
