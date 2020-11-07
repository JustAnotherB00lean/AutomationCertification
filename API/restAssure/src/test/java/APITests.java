
import org.testng.annotations.Test;
import pojo.Name;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class APITests extends BaseTest {

    private static final String baseURL = "https://api-coffee-testing.herokuapp.com";
    private static final String UpdateNameResource = "/v1/examen/updateName";
    private static final String getRandomNameResource = "/v1/examen/randomName";
    private static final String sameNameResource = "/v1/examen/sameName";



    @Test
    public void statusCodeTest() {
    request.get(baseURL).then().statusCode(200).assertThat();
    }

    @Test
    public void updateNameWithoutBodyTest() {
        request.put(baseURL + UpdateNameResource).then().body("message", equalTo("Name was not provided"));
    }

    @Test
    public void getRandomNameNameWithoutAuthTest() {
        request.get(baseURL + getRandomNameResource).then().body("message", equalTo("Please login first"));
    }

    @Test
    public void getRandomNameNameWithAuthAndSameNameValidationTest() {

        Name testName = new Name("edge");
        request.given()
                .auth()
                .basic("testuser","testpass")
                .when()
                .get(baseURL + getRandomNameResource)
                .then().assertThat().body("name", notNullValue());

      request.given()
                  .contentType("application/json")
                  .body(testName)
              .when()
                .post(baseURL + sameNameResource)
              .then()
                .statusCode(200)
                .body("name" , notNullValue());

    }
}