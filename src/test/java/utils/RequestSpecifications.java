package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static data.APIData.BASE_URL;

public class RequestSpecifications {
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .log(LogDetail.ALL)
            .build();
}
