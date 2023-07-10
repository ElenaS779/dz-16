package api.tests;

import api.BaseApiTest;
import api.models.Bookingdates;
import api.models.CreateBooking;
import api.models.GetBookingById;
import api.models.UpdateBookingPrice;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.testng.AssertJUnit.*;

public class RestAssuredTests extends BaseApiTest {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .build();
    }
       @Test
    @Description("create a new booking")
    public void createNewBooking() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CreateBooking body = new CreateBooking().builder()
                .firstname("Mojra")
                .lastname("Salivan")
                .totalprice(150)
                .depositpaid(true)
                .bookingdates(new Bookingdates().builder()
                        .checkin(sdf.parse("2023-07-07"))
                        .checkout(sdf.parse("2024-07-07"))
                        .build())
                .additionalneeds("Breakfast")
                .build();

        Response response = RestAssured.given().log().all()
                .body(body)
                .post("/booking");

        response.prettyPrint();
        response.then().statusCode(200);
        assertTrue(response.jsonPath().getString("booking.firstname").contains("Mojra"));
        assertTrue(response.jsonPath().getString("booking.lastname").contains("Salivan"));
        assertTrue(response.jsonPath().getString("booking.totalprice").contains("150"));
        assertTrue(response.jsonPath().getString("booking.depositpaid").contains("true"));
        assertTrue(response.jsonPath().getString("booking.additionalneeds").contains("Breakfast"));
    }
    @Test
    @Description("receive all booking ids")
    public void getAllBookingTest(){
        Response response = RestAssured.get("/booking");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    @Description("update field Price")
    public void updateBookingPriceTest(){
        Integer bookingId = getBookingIds().get(0);
        System.out.println("bookingId:" + bookingId);
        UpdateBookingPrice body = new UpdateBookingPrice().builder()
                .totalprice(100)
                .build();

        Response response = RestAssured.given().log().all()
                .body(body)
                .patch("/booking/{id}", bookingId);

        response.prettyPrint();
        response.then().statusCode(200);
        assertTrue(response.jsonPath().getString("totalprice").contains("100"));
    }
    @Test
    @Description("update fields Firstname and Additionalneeds")
    public void updateBookingTest() throws ParseException{
        Integer bookingId = getBookingIds().get(12);
        GetBookingById booking = getBookingById(bookingId);
        booking.setFirstname("Peter");
        booking.setAdditionalneeds("History");
        Response response = RestAssured.given()
                .log().all()
                .body(booking)
                .put("/booking/{id}",bookingId);

        response.prettyPrint();
        response.then().statusCode(200);
        assertTrue(response.jsonPath().getString("firstname").contains("Peter"));
        assertTrue(response.jsonPath().getString("additionalneeds").contains("History"));
    }
    @Test
    @Description("delete booking by id")
    public void deleteBookingByIdTest() {
        Integer bookingId = getBookingIds().get(7);
        Response response = RestAssured.given().log().all()
                .delete("/booking/{id}", bookingId);
        response.then().statusCode(201);
        response.prettyPrint();

    }

}
