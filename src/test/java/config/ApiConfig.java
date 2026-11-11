package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

/**
 * Настройки прогона, которые не хочется держать прямо в коде.
 * <p>
 * Owner читает {@code api.properties} с classpath. {@code System.getProperties()}
 * вторым аргументом даёт переопределить значение из командной строки, например
 * {@code -Dbase.url=http://localhost:3002}: свойство системы важнее файла.
 * <p>
 * Документация: <a href="http://owner.aeonbits.org/docs/usage/">Owner usage</a>.
 */
@Config.Sources("classpath:config.properties")
public interface ApiConfig extends Config {


    /**
     * Один экземпляр на весь прогон. Повторно файл не разбирается.
     */
    ApiConfig INSTANCE = ConfigFactory.create(ApiConfig.class, System.getProperties());

    /**
     * Базовый хост API, свойство {@code base.url}.
     * В спецификации это {@code setBaseUri}.
     *
     * @return хост, например {@code https://demoqa.ru}
     */
    @Key("base.url")
    String baseUrl();
}
