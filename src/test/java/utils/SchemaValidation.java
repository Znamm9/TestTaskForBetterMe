package utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

public class SchemaValidation {
    public static void validateSchema(Response response, String path) {
        File schemaFile = null;
        try {
            schemaFile = new File(path);
        } catch (Exception e) {
            throw new RuntimeException("" + e);
        }
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schemaFile));
    }
}
