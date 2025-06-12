package com.example.api.client;

import com.example.api.model.MobilePhoneItem;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;


public class BaseMethodApi {
    private static BaseMethodApi instance;

    private BaseMethodApi() {
        RestAssured.baseURI = "https://api.restful-api.dev";
    }

    public static BaseMethodApi getInstance() {
        if (instance == null) {
            instance = new BaseMethodApi();
        }
        return instance;
    }

    public Response createObject(MobilePhoneItem item) {
        return RestAssured.given()
                .contentType(JSON)
                .body(item)
                .post("/objects");
    }

    public Response getObjectById(String id) {
        return RestAssured.given()
                .get("/objects/" + id);
    }

    public Response listObjects() {
        return RestAssured.given()
                .get("/objects");
    }

    public Response deleteObjectById(String id) {
        return RestAssured.given()
                .delete("/objects/" + id);
    }


}
