package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources("classpath:config.properties")
public interface ApiConfig extends Config {

    ApiConfig INSTANCE = ConfigFactory.create(ApiConfig.class, System.getProperties());

    @Key("base.url")
    String baseUrl();

    @Key("base.page.api")
    String basePageApi();

    /**
     * Выключатель консольного логирования HTTP (RequestLoggingFilter/ResponseLoggingFilter).
     * AllureRestAssured прикладывает запросы и ответы в отчёт независимо от этого флага,
     * поэтому в CI логи можно держать выключенными и включать только для локальной отладки:
     * строка api.logging.enabled=true в config.properties или -Dapi.logging.enabled=true.
     */
    @Key("api.logging.enabled")
    @Config.DefaultValue("false")
    boolean apiLoggingEnabled();
}
