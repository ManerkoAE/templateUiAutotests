package common.api.endpoints;

/**
 * Пути методов из swagger DemoQA.
 * <p>
 * Банковские константы — это хвост после {@code /api/bank}. Этот префикс уже
 * задан в {@link common.api.specs.ApiSpecs}, поэтому в запрос уходит, например,
 * {@code /login}, а не {@code /api/bank/login}.
 * <p>
 * Account и BookStore лежат не под {@code /api/bank}. У них в константе полный
 * путь из swagger. С банковской спецификацией их склеивать нельзя: получится
 * {@code /api/bank/Account/...}. Для них нужна спецификация без этого base path.
 * <p>
 * Фигурные скобки — путь-параметр RestAssured. Имя внутри скобок должно совпасть
 * с {@code pathParam}:
 * <pre>{@code
 * given()
 *     .spec(ApiSpecs.request())
 *     .pathParam("id", userId)
 *     .get(Endpoint.USER.path());
 * }</pre>
 * Документация:
 * <a href="https://github.com/rest-assured/rest-assured/wiki/Usage#path-parameters">path parameters</a>.
 */
public enum Endpoint {

    // Банк. Путь дописывается к basePath "/api/bank".

    /** POST {@code /api/bank/register}. */
    REGISTER("/register"),

    /** POST {@code /api/bank/login}. */
    LOGIN("/login"),

    /** GET {@code /api/bank/user/{id}}. */
    USER("/user/{id}"),

    /** GET {@code /api/bank/search}. */
    SEARCH("/search"),

    /** GET {@code /api/bank/transactions/{userId}}. */
    TRANSACTIONS("/transactions/{userId}"),

    /** POST {@code /api/bank/transfer}. */
    TRANSFER("/transfer"),

    /** GET и POST {@code /api/bank/users/{userId}/loan-offers}. */
    USER_LOAN_OFFERS("/users/{userId}/loan-offers"),

    /** GET {@code /api/bank/loan-products/standard}. */
    STANDARD_LOAN_PRODUCT("/loan-products/standard"),

    /** DELETE {@code /api/bank/users/{userId}/loan-offers/{offerId}}. */
    USER_LOAN_OFFER("/users/{userId}/loan-offers/{offerId}"),

    /** POST {@code /api/bank/loan-quotes}. */
    LOAN_QUOTES("/loan-quotes"),

    /** POST {@code /api/bank/loan-applications}. */
    LOAN_APPLICATIONS("/loan-applications"),

    /** GET {@code /api/bank/users/{userId}/loan-applications}. */
    USER_LOAN_APPLICATIONS("/users/{userId}/loan-applications"),

    /** GET {@code /api/bank/loan-applications/{id}}. */
    LOAN_APPLICATION("/loan-applications/{id}"),

    /** POST {@code /api/bank/loan-applications/{id}/otp/resend}. */
    LOAN_APPLICATION_OTP_RESEND("/loan-applications/{id}/otp/resend"),

    /** POST {@code /api/bank/loan-applications/{id}/confirm}. */
    LOAN_APPLICATION_CONFIRM("/loan-applications/{id}/confirm"),

    /** POST {@code /api/bank/loan-applications/{id}/cancel}. */
    LOAN_APPLICATION_CANCEL("/loan-applications/{id}/cancel"),

    /** GET {@code /api/bank/users/{userId}/loans}. */
    USER_LOANS("/users/{userId}/loans"),

    /** GET {@code /api/bank/status}. */
    STATUS("/status");

    private final String path;

    Endpoint(String path) {
        this.path = path;
    }

    /**
     * Путь, который передаётся в {@code get}, {@code post}, {@code put} или {@code delete}.
     *
     * @return путь из swagger, для банка — без префикса {@code /api/bank}
     */
    public String path() {
        return path;
    }
}
