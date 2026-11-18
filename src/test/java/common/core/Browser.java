package common.core;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public enum Browser {

    CHROME,
    FIREFOX;

    public String browserName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static Browser from(String browserName) {
        return Arrays.stream(values())
                .filter(browser -> browser.name().equalsIgnoreCase(browserName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Неизвестный браузер: '%s'. Доступны: %s".formatted(browserName, supportedNames())));
    }

    private static String supportedNames() {
        return Arrays.stream(values())
                .map(Browser::browserName)
                .collect(Collectors.joining(", "));
    }
}
