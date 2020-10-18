package serenity.serenity_demo_stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import serenity.serenity_steps.DemoBlazeSteps;

public class DemoBlazeStepDefs {
	
	@Steps
	DemoBlazeSteps customer;

	@Given("customer is on {string}")
	public void i_am_on(String string) {
		
		customer.nav(string);
		
	}

	
	@When("customer navigates to {string}")
	public void customer_navigates_to(String laptop) {
		customer.catNav(laptop);
	}

	@When("Add to cart product {string}")
	public void add_to_cart_product(String product) {
		customer.addToCart(product);
	}
	
	@When("Add to cart second product {string}")
	public void add_to_cart_second_product(String product) {
		customer.add_to_cart_second_product(product);
	}

	@When("Delete {string} from cart")
	public void delete(String string) {
	   customer.deleteItem(string);
	}

	@When("Proceed for Place order")
	public void proceed_for_place_order() {
		
		customer.proceed_for_place_order();
	}

	@When("Fill all details")
	public void fill_all_details() {
	  
		customer.fill_all_details();
	}

	@When("Complete the order")
	public void complete_the_order() {
		customer.complete_the_order();
	}

	@Then("Order amount should be equal to product price")
	public void order_amount_should_be_equal_to_product_price() {
	   customer.order_amount_should_be_equal_to_product_price();
	}

	@Then("Click on Ok")
	public void click_on_ok() {
	   customer.click_on_ok();
	}
}
