package common.api.specs;

import config.ApiConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;


/**
 * Общая спецификация запросов к банку.
 * <p>
 * Спецификация собирает то, что повторяется в каждом вызове: хост, префикс
 * {@code /api/bank}, JSON и фильтры. В шаге остаётся тело и конкретный путь
 * из {@link common.api.endpoints.Endpoint}.
 * <p>
 * Документация:
 * <a href="https://github.com/rest-assured/rest-assured/wiki/Usage#specification-re-use">specification re-use</a>.
 */
public final class ApiSpecs {

    /**
     * Готовая спецификация. Создаётся один раз и переиспользуется.
     * <p>
     * {@code setConfig} нужен, потому что RestAssured приносит Gson, а проект
     * сериализует POJO через Jackson. Если на classpath оба маппера и ни один
     * не выбран, отправка объекта в {@code body} падает. Пустой
     * {@code RestAssuredConfig.config()} этот выбор не делает.
     * <p>
     * {@link RequestLoggingFilter} пишет запрос в консоль, {@link ResponseLoggingFilter}
     * пишет ответ. {@link AllureRestAssured} кладёт их же во вложения отчёта Allure,
     * в консоль он ничего не печатает.
     * <p>
     * Документация:
     * <a href="https://github.com/rest-assured/rest-assured/wiki/Usage#object-mapping">object mapping</a>,
     * <a href="https://github.com/rest-assured/rest-assured/wiki/Usage#logging">logging</a>,
     * <a href="https://allurereport.org/docs/restassured/">Allure RestAssured</a>.
     */
    private static final RequestSpecification REQUEST = new RequestSpecBuilder()
            .setBaseUri(ApiConfig.INSTANCE.baseUrl())
            .setBasePath("/api/bank")
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .setConfig(RestAssuredConfig.config()
                    .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.JACKSON_2)))
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new AllureRestAssured())
            .build();

    private ApiSpecs() {
    }

    /**
     * Спецификация для {@code given().spec(...)}.
     *
     * @return общая спецификация банковских запросов
     */
    public static RequestSpecification request() {
        return REQUEST;
    }
}
