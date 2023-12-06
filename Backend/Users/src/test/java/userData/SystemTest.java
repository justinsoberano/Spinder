package userData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import userData.chat.ChatRoom.ChatRoom;
import userData.chat.ChatRoom.Message;
import userData.stations.Station;
import userData.trackCreation.Track.Track;
import userData.users.User;
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
    public void getUsername() {
        // Send request and receive response
        Response response = RestAssured.given().
                header("Content-Type", "text/plain").
                header("charset","utf-8").
                when().
                get("/user/testUser");


        // Check status code
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

    }

    @Test
    public void testTrack(){
        Track t = new Track("id", "song", "album", "artist", "image", "preview");
        assertEquals("id", t.getId());
        assertEquals("song", t.getName());
        assertEquals("album", t.getAlbum());
        assertEquals("artist", t.getArtist());
        assertEquals("image", t.getImage());
        assertEquals("preview", t.getPreview());
    }

    @Test
    public void testMessage(){
        Message m = new Message("tom", "hi");
        assertEquals("hi", m.getContent());
        assertEquals("tom", m.getUser());
        m.setId(1);
        assertEquals(1,m.getId());
    }

    @Test
    public void testChatRoom(){
        ChatRoom c = new ChatRoom(1, "tom", "bob");
        assertEquals(1, c.getId());
        assertEquals("tom", c.getUserOne());
        assertEquals("bob", c.getUserTwo());
        c.setUserOne("bob");
        c.setUserTwo("tom");
        assertEquals("bob", c.getUserOne());
        assertEquals("tom", c.getUserTwo());
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


    @Test
    public void station_creation() {
        Track t = null;
        Station s = new Station(1);
        s.setSeed(t);
        s.setPopularity(10);
        s.setTempo(10);
        s.setVolume(10);
        s.setId(2);

        assertNull(s.getSeed());
        assertEquals(10, s.getTempo());
        assertEquals(10, s.getVolume());
        assertEquals(10, s.getPopularity());
        assertEquals(2, s.getId());
    }

    @Test
    public void user_creation() {
        User u = new User(1, "1", "1", "1", "1");
        u.setPassword("1");
        u.setPlaylistId("1");
        u.setUuid("1");
        u.setName("1");
        u.setProfileStr("1");
        u.setAccessKey("1");
        u.setStation(null);
        u.setTopTrack(null);
        u.setTopArtist(null);
        u.setId(1);
        u.setUserName("1");
        u.setProfilePicture("1");

        assertEquals("1", u.getUserName());
        assertEquals(1, u.getId());
        assertEquals("1", u.getPlaylistId());
        assertEquals("1", u.getAccessKey());
        assertEquals("1", u.getName());
        assertEquals("1", u.getProfilePicture());
        assertEquals("1", u.getUuid());
        assertEquals("1", u.getProfileStr());
        assertEquals("1", u.getPassword());
        assertNull(u.getTopArtist());
        assertNull(u.getTopTrack());
        assertNull(u.getFriends());
        assertNull(u.getStation());
    }


}
