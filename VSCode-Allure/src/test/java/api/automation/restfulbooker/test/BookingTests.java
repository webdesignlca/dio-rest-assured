package api.automation.restfulbooker.test;

import Entities.Booking;
import Entities.BookingDates;
import Entities.User;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static io.restassured.config.LogConfig.logConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

public class BookingTests {
    public static Faker faker;
    private static RequestSpecification request;
    private static Booking booking;
    private static BookingDates bookingDates;
    private static User user;

    @BeforeAll
    public static void Setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        faker = new Faker();
        user = new User(faker.name().username(), faker.name().firstName(),
                faker.name().lastName(), faker.internet().safeEmailAddress(),
                faker.internet().password(8, 10),
                faker.phoneNumber().toString());

        bookingDates = new BookingDates(faker.date().toString(), faker.date().toString());

        booking = new Booking(user.getFirstName(), user.getLastName(),
                (float) faker.number().randomDouble(2, 911111111, 999999999),
                true, bookingDates,
                "");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }

    @BeforeEach
    void setRequest() {
        request = given()
                .config(RestAssured.config()
                        .logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .contentType(ContentType.JSON)
                .auth().basic("admin", "password123");
    }

    @Test
    public void getAllBookingsById_returnOk(){
            Response response = request
                                    .when()
                                        .get("/booking")
                                    .then()
                                        .extract()
                                        .response();


        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void  getAllBookingsByUserFirstName_BookingExists_returnOk(){
                    request
                        .when()
                            .queryParam("firstName", "larissa")
                            .get("/booking")
                        .then()
                            .assertThat()
                            .statusCode(200)
                            .contentType(ContentType.JSON)
                        .and()
                        .body("results", hasSize(greaterThan(0)));

    }

    @Test
    public void  CreateBooking_WithValidData_returnOk(){
        request.when()
                .body(booking)
                .post("/booking")
                .then()
                .body(matchesJsonSchemaInClasspath("createBookingRequestSchema.json"))
                .and()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON).and().time(lessThan(40000L));



    }
}
