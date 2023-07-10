package api;
import api.models.GetBookingById;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
public class BaseApiTest {

    public List<Integer> getBookingIds(){
        Response response = RestAssured.get("/booking");
        return response.body().jsonPath().getList("bookingid");
    }

    public GetBookingById getBookingById(Integer bookingId){
        Response response = RestAssured.get("/booking/{id}", bookingId);
        return response.body().jsonPath().getObject(".", GetBookingById.class);
    }
}
