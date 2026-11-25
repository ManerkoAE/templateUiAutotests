package common.api.specs;

import config.ApiConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public final class ApiSpecs {

    private static final List<Filter> FILTERS = createFilters();

    private static final RequestSpecification REQUEST = new RequestSpecBuilder()
            .setBaseUri(ApiConfig.INSTANCE.baseUrl())
            .setBasePath(ApiConfig.INSTANCE.basePageApi())
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addFilters(FILTERS)
            .build();

    private ApiSpecs() {
    }

    public static RequestSpecification request() {
        return REQUEST;
    }

    private static List<Filter> createFilters() {
        if (ApiConfig.INSTANCE.apiLoggingEnabled()) {
            return List.of(
                    new RequestLoggingFilter(),
                    new ResponseLoggingFilter(),
                    new AllureRestAssured());
        }
        return List.of(new AllureRestAssured());
    }
}