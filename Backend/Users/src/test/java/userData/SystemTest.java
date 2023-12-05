package userData;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server;
//import org.springframework.boot.web.server.LocalServerPort;
	// SBv3

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class SystemTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void spotifyLoginTest() {
        Response r = RestAssured.given()
                .header("Content-Type", "text/plain")
                .header("charset", "utf-8")
                .when()
                .get("login");

        int statusCode = r.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void spotifyRegisterTest() {
        Response r = RestAssured.given()
                .header("Content-Type", "text/plain")
                .header("charset", "utf-8")
                .when()
                .get("register/user");

        int statusCode = r.getStatusCode();
        assertEquals(200, statusCode);
    }
}
