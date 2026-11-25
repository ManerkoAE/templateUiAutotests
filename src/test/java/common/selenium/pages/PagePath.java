package common.selenium.pages;


public enum PagePath {

    BANK(""),
    LOGIN("/login"),
    REGISTER("/register");

    private final String value;

    PagePath(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value.isEmpty() ? "/" : value;
    }
}
