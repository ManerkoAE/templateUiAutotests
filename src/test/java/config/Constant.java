package config;

public class Constant {

    public static class Timeouts {
        public static final int IMPLICIT_WAIT = 5;
        public static final int EXPLICIT_WAIT = 10;
    }

    public static class Urls {
        public static final String BANK_LOANS_URL = "https://demoqa.ru/bank?lang=en";
    }
    public static class ExpectedMessages {
        public static final String WRONG_CREDENTIALS_ERROR = "Неверные учетные данные";
    }
}
