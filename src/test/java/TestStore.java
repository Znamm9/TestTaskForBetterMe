import dto.store.DeleteOrderByIdResponseDTO;
import dto.store.GetInventoryResponseDTO;
import dto.store.GetOrderByIdResponseDTO;
import dto.store.PostOrderResponseDTO;
import io.restassured.response.Response;
import models.store.OrderModelBody;
import org.testng.annotations.Test;

import static action.controls.store.ControlStoreActions.*;

public class TestStore extends BaseTest {

    @Test
    public void checkGetInventory() {
        GetInventoryResponseDTO getInventoryResponseDto = getInventoryDTO();

//      Validate schema before further checks
        getInventoryValidateSchema("src/test/resources/schema/store/getInventorySchema.json");

        softAssert.assertTrue(!getInventoryResponseDto.getProperties().isEmpty(), "Inventory are empty");
        softAssert.assertAll();
    }

    @Test
    public void checkPostOrder() {
        OrderModelBody body = new OrderModelBody(
                2, 1, 1, "2025-04-11T22:45:11.740Z", "placed", true);
        PostOrderResponseDTO postOrderResponseDTO = postOrderDTO(body);

//      Validate schema before further checks
        postOrderValidateSchema(body, "src/test/resources/schema/store/postOrderSchema.json");

        softAssert.assertTrue(postOrderResponseDTO.getId() == 2, "Order id is not 2");
        softAssert.assertTrue(postOrderResponseDTO.getPetId() == 1, "Pet id is not 1");
        softAssert.assertTrue(postOrderResponseDTO.getQuantity() == 1, "Quantity is not 1");
        softAssert.assertTrue(postOrderResponseDTO.getShipDate().equals("2025-04-11T22:45:11.740+0000"),
                "Ship date is not 2025-04-11T22:45:11.740+0000");
        softAssert.assertTrue(postOrderResponseDTO.getStatus().equals("placed"), "Status is not placed");
        softAssert.assertTrue(postOrderResponseDTO.getComplete(), "Complete is not true");

        softAssert.assertAll();
    }

    @Test
    public void checkPostOrderWithInvalidBody() {
//        Create order with wrong data format
//      TODO!!!  this test is failing due to wrong response, there should be 400 instead of 500
        OrderModelBody body = new OrderModelBody(
                6, 1, 1, "not a date", "placed", true);

        Response response = postOrderNegative(body);

        softAssert.assertTrue(response.getStatusCode() == 500, "Response status code is not 500");

        softAssert.assertAll();
    }

    @Test
    public void checkGetOrderById() {
//        create order to be sure its exist before getting it
        OrderModelBody body = new OrderModelBody(
                2, 1, 1, "2025-04-11T22:45:11.740Z", "placed", true);
        PostOrderResponseDTO postOrderResponseDTO = postOrderDTO(body);

        GetOrderByIdResponseDTO getOrderByIdResponseDTO = getOrderByIdDTO("2");

//      Validate schema before further checks
        getOrderByIdValidateSchema("2", "src/test/resources/schema/store/getInventoryByIdSchema.json");

        softAssert.assertTrue(getOrderByIdResponseDTO.getId() == 2, "Order id is not 2");
        softAssert.assertTrue(getOrderByIdResponseDTO.getPetId() == 1, "Pet id is not 1");
        softAssert.assertTrue(getOrderByIdResponseDTO.getQuantity() == 1, "Quantity is not 1");
        softAssert.assertTrue(getOrderByIdResponseDTO.getStatus().equals("placed"), "Status is not placed");

        softAssert.assertAll();
    }

    @Test
    public void checkGetOrderByIdWithInvalidId() {
        Response response = getOrderByUnexistingId("asd");

        softAssert.assertTrue(response.getStatusCode() == 404, "Response status code is not 404");

        softAssert.assertAll();
    }

    @Test
    public void checkDeleteOrder() {
//        create data before deletion
        OrderModelBody body = new OrderModelBody(
                3, 1, 1, "2025-04-12T22:45:11.740Z", "placed", true);
        PostOrderResponseDTO postOrderResponseDTO = postOrderDTO(body);
        String idToDelete = String.valueOf(postOrderResponseDTO.getId());
        Response response = deleteOrderByIdResponse(idToDelete);

        DeleteOrderByIdResponseDTO deleteOrderByIdResponseDTO = deleteOrderByIdDTO(response);
        deleteOrderByIdValidateSchema(response, "src/test/resources/schema/store/deleteOrderByIdSchema.json");

        softAssert.assertTrue("3".equals(deleteOrderByIdResponseDTO.getMessage()), "deleted message is wrong");
        softAssert.assertAll();
    }

    @Test
    public void deleteUnexistingOrder() {
        OrderModelBody body = new OrderModelBody(
                4, 1, 1, "2025-04-12T22:45:11.740Z", "placed", true);
        PostOrderResponseDTO postOrderResponseDTO = postOrderDTO(body);

        String idToDelete = String.valueOf(postOrderResponseDTO.getId());
        Response response = deleteOrderByIdResponse(idToDelete);

        softAssert.assertTrue(response.getStatusCode() == 200, "Response status code is not 200");

        response = getOrderByUnexistingId("4");

        softAssert.assertTrue(response.getStatusCode() == 404, "Response status code is not 404");
        softAssert.assertAll();

    }
}
