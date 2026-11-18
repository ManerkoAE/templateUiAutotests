package common.api.support;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * Фабрика WireMock-заглушек для банковских API-тестов.
 * <p>
 * Расширение в JUnit 5 обязано быть полем тест-класса с {@code @RegisterExtension},
 * поэтому целиком спрятать его в конфиг нельзя: у каждого теста своя папка сценариев.
 * Фабрика убирает повторяющуюся сборку опций и оставляет в тесте одну строку:
 * <pre>{@code
 * @RegisterExtension
 * static WireMockExtension wm = BankWireMock.stub("standard_loan_product-success");
 * }</pre>
 * Порт фиксирован 3002 — на него смотрит {@code base.url} из {@link config.ApiConfig}.
 * <p>
 * Документация:
 * <a href="https://wiremock.org/docs/junit-jupiter/">WireMock JUnit 5</a>.
 */
public final class BankWireMock {

    /** Порт заглушки: совпадает с {@code base.url} в конфиге проекта. */
    private static final int STUB_PORT = 3002;

    private BankWireMock() {
    }

    /**
     * Заглушка со сценариями из {@code scenarios/<папка>} без шаблонизации тел.
     *
     * @param scenarioFolder имя папки со сценариями, например {@code "status-success"}
     * @return расширение WireMock для поля тест-класса с {@code @RegisterExtension}
     */
    public static WireMockExtension stub(final String scenarioFolder) {
        return stub(scenarioFolder, false);
    }

    /**
     * Заглушка со сценариями из {@code scenarios/<папка>}.
     *
     * @param scenarioFolder   имя папки со сценариями, например {@code "transfer-success"}
     * @param globalTemplating {@code true} включает шаблонизацию тел ответов
     *                         ({@code {{jsonpath ...}}}, {@code {{faker ...}}} и т.п.)
     * @return расширение WireMock для поля тест-класса с {@code @RegisterExtension}
     */
    public static WireMockExtension stub(final String scenarioFolder, final boolean globalTemplating) {
        return WireMockExtension.newInstance()
                .options(wireMockConfig()
                        .port(STUB_PORT)
                        .usingFilesUnderClasspath("scenarios/" + scenarioFolder)
                        .globalTemplating(globalTemplating))
                .build();
    }
}
