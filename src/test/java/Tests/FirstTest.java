package Tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.AbstractHelper;
import utils.DoCContext;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FirstTest extends AbstractHelper {
    private DoCContext context;
    private Response response;
    private int count;
    Map<String,String> mapOfCards = new HashMap<>();

    @BeforeClass
    public void setUp() {
        logger.info("====================Scenario Starts Here====================");
        context = DoCContext.builder().build();
    }

    @Parameters({"count"})
    @BeforeTest
    public void setParams(@Optional("5") int number) {
        count = number;
    }

    @Test
    public void shuffleTheCardWithDeck_countDeck_count() {
        logger.info("drawCountCountOfCardUsingDeck_id");
            context.setCardsCount(count);
            context.setDeck_id("new");
        sendGetRequest(context.getDeck_id() + properties.getProperty("draw.endpoint") + count, 200);
        logger.info("drawCountCountOfCardUsingDeck_id response is : " + response.prettyPrint());

        logger.info("parse values and suits into map...");
        JSONArray cards = new JSONObject(response.prettyPrint()).getJSONArray("cards");
        for (int i = 0; i < cards.length(); i++) {
            JSONObject cartObj = cards.getJSONObject(i);
            mapOfCards.put(cartObj.getString("value"), cartObj.getString("suit"));
        }

        logger.info("Print all values from map");
        int size = mapOfCards.size();
        logger.info(String.valueOf(size));

        for (Map.Entry<String, String> entry : mapOfCards.entrySet()) {
            logger.info("Nothing found in db for A with a : "+entry.getKey()+" and b : "+entry.getValue());
        }
    }

        public void sendGetRequest (String endpoint,int expectedStatusCode){
            response = given()
                    .baseUri(baseUrl)
                    .when()
                    .get(endpoint)
                    .then()
                    .assertThat()
                    .statusCode(expectedStatusCode)
                    .extract().response();
        }
    }
