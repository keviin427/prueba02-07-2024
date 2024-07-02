package glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class StoreOrderSteps {
    private Response response;
    private RequestSpecification request;
    private final String BASE_URL = "https://petstore.swagger.io/v2";

    @Given("creo una nueva orden con id {int}, petId {int} y cantidad {int}")
    public void que_creo_una_nueva_orden_con_id_petId_y_cantidad(int orderId, int petId, int cantidad) {
        request = RestAssured.given();
        Map<String, Object> order = new HashMap<>();
        order.put("id", orderId);
        order.put("petId", petId);
        order.put("quantity", cantidad); // Cambiado de "cantidad" a "quantity"
        request.header("Content-Type", "application/json");
        request.body(order);
    }

    @When("envío la solicitud para crear la orden")
    public void envio_la_solicitud_para_crear_la_orden() {
        response = request.post(BASE_URL + "/store/order");
    }

    @Then("debería recibir una respuesta con el código de estado {int}")
    public void deberia_recibir_una_respuesta_con_el_codigo_de_estado(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("el cuerpo de la respuesta de creación debería contener el id de la orden {int}")
    public void el_cuerpo_de_la_respuesta_de_creacion_deberia_contener_el_id_de_la_orden(int orderId) {
        Assert.assertEquals(orderId, response.jsonPath().getInt("id"));
    }

    @Given("tengo una orden existente con id {int}")
    public void tengo_una_orden_existente_con_id(int orderId) {
        request = RestAssured.given();
        request.pathParam("orderId", orderId);
    }

    @When("envío la solicitud para consultar la orden")
    public void envio_la_solicitud_para_consultar_la_orden() {
        response = request.get(BASE_URL + "/store/order/{orderId}");
    }

    @Then("el cuerpo de la respuesta debería contener el id de la orden {int}")
    public void el_cuerpo_de_la_respuesta_debería_contener_el_id_de_la_orden(int orderId) {
        Assert.assertEquals(orderId, response.jsonPath().getInt("id"));
    }


}

