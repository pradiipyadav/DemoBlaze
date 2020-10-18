package serenity.serenity_demo_stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.util.EnvironmentVariables;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;


import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PetStoreStepDefs {

	

	private Response response;

	private EnvironmentVariables environmentVariables;
	private String theRestApiBaseUrl;

	private String pet;
	private String findby;
	
	RequestSpecification requestSpecification;

	private int random;

	private String status;
	
    private static final Logger LOGGER = LogManager.getLogger();

	
	
	@Before
	public void setEnv() {
		theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl") 
	             .orElse("https://petstore.swagger.io/v2/");
	}
	
	@Given("I have access to {string}, {string}")
	public void i_have_access_to(String pet, String findby) {
		
		this.pet = pet;
		this.findby = findby;
	}

	@When("I request for all {string} pets")
	public void i_request_for_all_pets(String status) {
		response = SerenityRest.given()
				.baseUri(theRestApiBaseUrl)
				.header("Content-Type", "application/json")
				.pathParam("pet", this.pet)
				.pathParam("findBy", this.findby)
				.queryParam("status", status)
				.when()
				.get("{pet}/{findBy}");
				response.prettyPrint();
				
	}

	
	@Then("I should see all the available pets")
	public void i_should_see_all_the_pets() {

		restAssuredThat(lastResponse -> lastResponse.statusCode(200));
		restAssuredThat(lastResponse -> lastResponse.body("name", is(notNullValue())));
		restAssuredThat(lastResponse -> lastResponse.body("status", hasItem("available")));
		
		
		 LOGGER.info("Pets " + response.prettyPrint());



	}
	
	@Given("I have access to {string}")
	public void i_have_access_to(String path) {

		requestSpecification = SerenityRest.given()
		.baseUri(theRestApiBaseUrl)
		.header("Content-Type", "application/json")
		.basePath(path);
	}

	@When("I Post a pets")
	public void i_post_a_pets() {
		
		random = new Random().nextInt(2000);
		System.out.println("Random id : " + random);
		requestSpecification.when().body("{\r\n"
				+ "  \"id\": "+random+",\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}").post();
	   
		SerenityRest.lastResponse().prettyPrint();
	}

	@Then("I should see Pet added")
	public void i_should_see_pet_added() {
	    
		 SerenityRest.given()
		.baseUri(theRestApiBaseUrl)
		.pathParam("petid", random)
		.get("pet/{petid}");
		 
		 SerenityRest.lastResponse().prettyPrint();
		
		 restAssuredThat(lastResponse -> lastResponse.statusCode(200));
		 
		 LOGGER.info("Pet added with id: " + random);


		
	}
	
	@When("I update pet status to {string}")
	public void i_update_pet_status_to(String status) {
		
		this.status = status;
		requestSpecification.when()
		.body("{\r\n"
				+ "  \"id\": "+random+",\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \""+status+"\"\r\n"
				+ "}")
		.put();
		
		SerenityRest.lastResponse().prettyPrint();


	}

	@Then("Pet status should be updated")
	public void pet_status_should_be_updated() {
	   
		SerenityRest.given()
		.baseUri(theRestApiBaseUrl)
		.pathParam("petid", random)
		.get("pet/{petid}");
		 
		 SerenityRest.lastResponse().prettyPrint();
		
		 restAssuredThat(lastResponse -> lastResponse.body("status", equalTo(status)));
		 
		 LOGGER.info("Pet status updated to: " + status);

	}

	@When("I delete the new pet")
	public void i_delete_the_new_pet() {
		
		SerenityRest.given()
		.baseUri(theRestApiBaseUrl)
		.pathParam("petid", random)
		.delete("pet/{petid}");
		SerenityRest.lastResponse().prettyPrint();

	}

	@Then("Pet should be deleted")
	public void pet_should_be_deleted() {

		SerenityRest.given()
		.baseUri(theRestApiBaseUrl)
		.pathParam("petid", random)
		.get("pet/{petid}");
		
		restAssuredThat(lastResponse -> lastResponse.statusCode(404));
		
		 LOGGER.info("Pet status deleted as get request " +theRestApiBaseUrl + "pet/" + random + " returns 404 ");


		
	}


}
