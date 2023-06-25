package StepDefinitions;

import Resources.TestData1;
import Resources.Utilities1;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class StepDef extends Utilities1 {
        ResponseSpecification respos;
        RequestSpecification res;
        Response resp;
        TestData1 td = new TestData1();
        static String name,job,ID;
    @Given("User has correct URLs and correct body as {string} and {string}")
    public void userHasCorrectURLsAndCorrectBodyAsAnd(String name, String job) throws IOException {

       //Building Request Spec for common elements such as Base URI, content type
        respos = new ResponseSpecBuilder().expectStatusCode(201).expectContentType("application/json").build();
        //using request spec in "given" statement
        res = given().spec(Utilis()).body(td.Body(name, job));
    }

    @When("User calls {string} API using {string} method")
    public void userCallsAPIUsingPOSTMethod(String APIname, String method) throws IOException {
        if (method.equalsIgnoreCase("POST")) {
            resp = res.when().post(getGlobalValue("POST")).then().spec(respos).extract().response();;
        }
        else if (method.equalsIgnoreCase("PUT"))
        {
            resp = res.when().put(getGlobalValue("PUT")).then().spec(respos).extract().response();;
        }

    }
    @Then("API call is success and Response code is {int}")
    public void apiCallIsSuccessAndResponseCodeIs(Integer int1) {
        assertEquals(resp.getStatusCode(), 201);

    }
    @And("New Employee details displayed are Name {string} and job is {string}")
    public void newEmployeeDetailsDisplayedAreNameAndJobIs(String arg0, String arg1) {
        String response1 = resp.asString();
        JsonPath js = new JsonPath(response1);
        ID = js.get("id");
        name = js.get("name");
        job = js.get("job");
        System.out.println("ID is "+ID);
        System.out.println("New Employee name is "+name);
        System.out.println("New Employee job is "+job);
    }
    @Given("Given User has correct URLs and correct body as {string} and {string}")
    public void givenUserHasCorrectURLsAndCorrectBodyAsAnd(String arg0, String arg1) throws IOException {
      res =  given().pathParam("id",ID).spec(Utilis()).body(td.updateEmp("Ram","Sr.Manager"));




    }
}
