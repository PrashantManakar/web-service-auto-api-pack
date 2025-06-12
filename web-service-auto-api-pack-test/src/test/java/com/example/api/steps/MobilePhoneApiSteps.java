package com.example.api.steps;

import com.example.api.client.BaseMethodApi;
import com.example.api.model.MobilePhoneItem;
import com.example.api.utils.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MobilePhoneApiSteps {

    private final TestContext context;
    private final BaseMethodApi apiClient;
    private Response response;

    public MobilePhoneApiSteps() {
        this.context = new TestContext();
        this.apiClient = BaseMethodApi.getInstance();
    }

    @Before
    public void setup() {
        context.clear();
    }

    @After
    public void cleanup() {
        for (String id : context.getCreatedObjectIds()) {
            apiClient.deleteObjectById(id)
                    .then()
                    .statusCode(anyOf(is(200), is(204), is(404)));
        }
    }

    @Given("a {string} item is created")
    public void a_item_is_created(String name) {
        MobilePhoneItem.Builder builder = new MobilePhoneItem.Builder().name(name);
        context.setCurrentObject(builder.build());
    }

    @Given("is a {string} CPU model")
    public void is_a_cpu_model(String cpu) {
        MobilePhoneItem current = context.getCurrentObject();
        MobilePhoneItem updated = new MobilePhoneItem.Builder()
                .id(current.getId())
                .name(current.getName())
                .cpu(cpu)
                .price(current.getPrice())
                .build();
        context.setCurrentObject(updated);
    }

    @Given("has a price of {string}")
    public void has_a_price_of(String priceStr) {
        double price = Double.parseDouble(priceStr);
        MobilePhoneItem current = context.getCurrentObject();
        MobilePhoneItem updated = new MobilePhoneItem.Builder()
                .id(current.getId())
                .name(current.getName())
                .cpu(current.getCpu())
                .price(price)
                .build();
        context.setCurrentObject(updated);
    }

    @When("the request to add the item is made")
    public void the_request_to_add_the_item_is_made() {
        response = apiClient.createObject(context.getCurrentObject());
        response.then().statusCode(200);
        String id = response.jsonPath().getString("id");
        context.addCreatedObjectId(id);

        MobilePhoneItem updated = new MobilePhoneItem.Builder()
                .id(id)
                .name(context.getCurrentObject().getName())
                .cpu(context.getCurrentObject().getCpu())
                .price(context.getCurrentObject().getPrice())
                .build();
        context.setCurrentObject(updated);
    }

    @Then("a {int} response code is returned")
    public void a_response_code_is_returned(int expectedStatus) {
        assertThat(response.getStatusCode(), is(expectedStatus));
    }

    @Then("a {string} is created")
    public void a_is_created(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        assertThat(actualName, equalTo(expectedName));
    }

    @Given("an existing item is created with name {string}")
    public void an_existing_item_is_created_with_name(String name) {
        MobilePhoneItem item = new MobilePhoneItem.Builder().name(name).build();
        response = apiClient.createObject(item);
        response.then().statusCode(200);
        String id = response.jsonPath().getString("id");
        context.addCreatedObjectId(id);

        MobilePhoneItem created = new MobilePhoneItem.Builder()
                .id(id)
                .name(name)
                .build();
        context.setCurrentObject(created);
    }

    @When("the request to get the item by id is made")
    public void the_request_to_get_the_item_by_id_is_made() {
        response = apiClient.getObjectById(context.getCurrentObject().getId());
    }

    @Then("the item name is {string}")
    public void the_item_name_is(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        assertThat(actualName, equalTo(expectedName));
    }

    @Given("multiple items are created")
    public void multiple_items_are_created() {
        for (int i = 1; i <= 2; i++) {
            MobilePhoneItem item = new MobilePhoneItem.Builder()
                    .name("Item " + i)
                    .build();
            Response res = apiClient.createObject(item);
            res.then().statusCode(200);
            String id = res.jsonPath().getString("id");
            context.addCreatedObjectId(id);
        }
    }

    @When("the request to list all items is made")
    public void the_request_to_list_all_items_is_made() {
        response = apiClient.listObjects();
    }

    @Then("the list contains at least {int} items")
    public void the_list_contains_at_least_items(int minCount) {
        ArrayList<Map<String, ?>> jsonAsArrayList = from(response.asString()).get("");
        assertThat(jsonAsArrayList.size(), equalTo(minCount));
    }

    @And("the below contains mobile phone details:")
    public void theBelowContainsMobilePhoneDetails(List<Map<String, String>> table) throws JsonProcessingException {
        SoftAssertions softAssertions = new SoftAssertions();
        table.forEach(actual -> {
            validateActualPhoneDetails(response, actual, softAssertions);
        });
        softAssertions.assertAll();
    }

    private void validateActualPhoneDetails(Response response, Map<String, String> actual, SoftAssertions softAssertions) {
        List<String> ids = response.path("id");
        List<String> nameList = response.path("name");
        softAssertions.assertThat(ids.contains(actual.get("id"))).isEqualTo(true);
        softAssertions.assertThat(nameList.contains(actual.get("name"))).isEqualTo(true);
    }


}

