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


public final class ApiSpecs {

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

    public static RequestSpecification request() {
        return REQUEST;
    }
}
