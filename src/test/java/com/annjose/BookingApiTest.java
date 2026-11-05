package com.annjose;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookingApiTest {

    private int bookingId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void createBooking() {
        Map<String, Object> booking = Map.of(
                "firstname", "Ann",
                "lastname", "Jose",
                "totalprice", 250,
                "depositpaid", true,
                "bookingdates", Map.of("checkin", "2026-12-01", "checkout", "2026-12-05"),
                "additionalneeds", "Breakfast");

        bookingId = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(booking)
                .when()
                .post("/booking")
                .then()
                .log().body()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("Ann"))
                .extract().path("bookingid");

        System.out.println("Created booking " + bookingId);
    }
}