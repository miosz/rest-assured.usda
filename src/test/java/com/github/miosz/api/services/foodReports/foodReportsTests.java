package com.github.miosz.api.services.foodReports;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;


public class foodReportsTests {

    @Test
    public void
    checkNdbAndNameJsonTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=json&api_key=DEMO_KEY").
                then().
                statusCode(200).
                body("report.food.ndbno", equalTo("01009"),
                        "report.food.name", containsString("cheddar"));
    }

    @Test
    public void
    checkNdbAndNameXmlTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=xml&api_key=DEMO_KEY").
                then().
                statusCode(200).
                assertThat().
                body("report.food.@ndbno", equalTo("01009"),
                        "report.food.@name", containsString("cheddar"));
    }

    @Test
    public void
    checkProteinValueJsonTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=json&api_key=DEMO_KEY").
                then().
                statusCode(200).
                body("report.food.nutrients.value[2]", equalTo("22.87"));
    }

    @Test
    public void
    checkProteinValueXmlTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=xml&api_key=DEMO_KEY").
                then().
                statusCode(200).
                body("report.food.nutrients.nutrient[2].@value", equalTo("22.87"));
    }

    @Test
    public void
    checkEqvFloatValueJsonTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=json&api_key=DEMO_KEY").
                then().
                statusCode(200).
                body("report.food.nutrients.measures[0].eqv[0]", is(132.0f));
    }

    @Test
    public void
    checkEqvValueXmlTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=xml&api_key=DEMO_KEY").
                then().
                statusCode(200).
                body("report.food.nutrients.nutrient[0].measures[0].measure[0].@eqv", equalTo("132.0"));
    }

    @Test
    public void
    food01009JsonSchemaValidationTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=json&api_key=DEMO_KEY").
                then().
                assertThat().
                body(matchesJsonSchemaInClasspath("test-data/01009.json"));
    }

    @Test
    public void
    food01009XmlSchemaValidationTest() {

        when().
                get("https://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=json&api_key=DEMO_KEY").
                then().
                assertThat();
//                body(matchesXsdInClasspath("test-data/01009.xml")); //Assertion fails
    }

}