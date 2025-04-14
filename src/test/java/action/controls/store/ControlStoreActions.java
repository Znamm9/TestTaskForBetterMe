package action.controls.store;

import controllers.store.StoreController;
import dto.store.DeleteOrderByIdResponseDTO;
import dto.store.GetOrderByIdResponseDTO;
import dto.store.GetInventoryResponseDTO;
import dto.store.PostOrderResponseDTO;
import io.restassured.response.Response;
import models.store.OrderModelBody;

import static controllers.store.StoreController.*;
import static utils.SchemaValidation.validateSchema;

public class ControlStoreActions {

    public static PostOrderResponseDTO postOrderDTO(OrderModelBody orderModelBody) {
        Response response = StoreController.postOrder(orderModelBody);
        return response.getBody().as(PostOrderResponseDTO.class);
    }

    public static Response postOrderNegative(OrderModelBody orderModelBody) {
        return StoreController.postOrderNegative(orderModelBody);
    }

    public static void postOrderValidateSchema(OrderModelBody body, String path) {
        Response response = postOrder(body);
        validateSchema(response, path);
    }

    public static GetInventoryResponseDTO getInventoryDTO() {
        Response response = StoreController.getInventory();
        return response.getBody().as(GetInventoryResponseDTO.class);
    }

    public static void getInventoryValidateSchema(String path) {
        Response response = getInventory();
        validateSchema(response, path);
    }

    public static GetOrderByIdResponseDTO getOrderByIdDTO(String idStr) {
        Response response = StoreController.orderById(idStr);
        return response.getBody().as(GetOrderByIdResponseDTO.class);
    }

    public static Response getOrderByUnexistingId(String idStr) {
        return StoreController.orderByUnexistingId(idStr);
    }

    public static void getOrderByIdValidateSchema(String idStr, String path) {
        Response response = orderById(idStr);
        validateSchema(response, path);
    }

    public static Response deleteOrderByIdResponse(String idStr) {
        return StoreController.deleteOrderById(idStr);
    }

    public static DeleteOrderByIdResponseDTO deleteOrderByIdDTO(Response previousDeleteResponse) {
        return previousDeleteResponse.getBody().as(DeleteOrderByIdResponseDTO.class);
    }

    public static void deleteOrderByIdValidateSchema(Response deleteResponse, String path) {
        validateSchema(deleteResponse, path);
    }
}
