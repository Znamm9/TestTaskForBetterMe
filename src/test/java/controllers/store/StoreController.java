package controllers.store;
import io.restassured.response.Response;
import models.store.OrderModelBody;

import static io.restassured.RestAssured.given;
import static utils.RequestSpecifications.requestSpecification;
import static utils.ResponseSpecifications.negativeResponseSpecification;
import static utils.ResponseSpecifications.responseSpecification;


public class StoreController {
    public static Response getInventory() {
        return given()
                .spec(requestSpecification)
                .when()
                .get("/store/inventory")
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }
    public static Response orderById(String idStr) {
        return given()
                .spec(requestSpecification)
                .when()
                .get("/store/order/" + idStr)
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

    public static Response postOrder(OrderModelBody body) {
        return given()
                .spec(requestSpecification)
                .body(body)
                .post("/store/order")
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

    public static Response postOrderNegative(OrderModelBody body) {
        return given()
                .spec(requestSpecification)
                .body(body)
                .post("/store/order")
                .then()
                .spec(negativeResponseSpecification)
                .extract()
                .response();
    }

    public static Response deleteOrderById(String idStr) {
        return given()
                .spec(requestSpecification)
                .when()
                .delete("/store/order/" + idStr)
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }

    public static Response getOrderByUnexistingId(String idStr) {
        return given()
                .spec(requestSpecification)
                .when()
                .delete("/store/order/" + idStr)
                .then()
                .extract()
                .response();
    }

    public static Response orderByUnexistingId(String idStr) {
        return given()
                .spec(requestSpecification)
                .when()
                .delete("/store/order/" + idStr)
                .then()
                .extract()
                .response();
    }
}
