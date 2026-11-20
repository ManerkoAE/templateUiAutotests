package common.api.endpoints;

public enum Endpoint {

    REGISTER("/register"),
    LOGIN("/login"),
    USER("/user/{id}"),
    SEARCH("/search"),
    TRANSACTIONS("/transactions/{userId}"),
    TRANSFER("/transfer"),
    USER_LOAN_OFFERS("/users/{userId}/loan-offers"),
    STANDARD_LOAN_PRODUCT("/loan-products/standard"),
    USER_LOAN_OFFER("/users/{userId}/loan-offers/{offerId}"),
    LOAN_QUOTES("/loan-quotes"),
    LOAN_APPLICATIONS("/loan-applications"),
    USER_LOAN_APPLICATIONS("/users/{userId}/loan-applications"),
    LOAN_APPLICATION("/loan-applications/{id}"),
    LOAN_APPLICATION_OTP_RESEND("/loan-applications/{id}/otp/resend"),
    LOAN_APPLICATION_CONFIRM("/loan-applications/{id}/confirm"),
    LOAN_APPLICATION_CANCEL("/loan-applications/{id}/cancel"),
    USER_LOANS("/users/{userId}/loans"),
    STATUS("/status");

    private final String path;

    Endpoint(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
