package com.github.miosz.api.services.foodReports;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class foodReportsTests {

    @Test
    public void
    firstExampleCallTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=json&api_key=DEMO_KEY").
                then().
                statusCode(200).
                body("report.food.ndbno", equalTo("01009"),
                        "report.food.name", containsString("cheddar"));

    }

}
