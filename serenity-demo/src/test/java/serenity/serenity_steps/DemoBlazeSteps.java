package serenity.serenity_steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.thucydides.core.annotations.Step;
import serenity.serenity_pages.DemoBlazeIndex;

public class DemoBlazeSteps {

	DemoBlazeIndex demoBlazeIndex;
	private String catName;
    private static final Logger LOGGER = LogManager.getLogger();

	@Step
	public void nav(String url) {
		demoBlazeIndex.nav(url);
		LOGGER.info("Customer navigated to url: " + url);

	}
	
	@Step
	public void catNav(String catName) {
		demoBlazeIndex.catName(catName);
		this.catName = catName;
		LOGGER.info("Customer navigated to category: " + catName);
	}

	@Step
	public void addToCart(String product) {
		demoBlazeIndex.addToCart(product);
		LOGGER.info("Customer added: "+ product + " to cart");
	}

	@Step
	public void add_to_cart_second_product(String product) {
		
		demoBlazeIndex.add_to_cart_second_product(product);
		LOGGER.info("Customer added: "+ product + " to cart");

	}

	@Step
	public void deleteItem(String prod) {
		
		demoBlazeIndex.deleteItem(prod);
		LOGGER.info("Customer deleted: "+ prod + " from cart");
	}

	@Step
	public void proceed_for_place_order() {
		
		demoBlazeIndex.proceed_for_place_order();
		LOGGER.info("Customer clicked on \"Place order\".");
	}

	@Step
	public void fill_all_details() {

		demoBlazeIndex.fill_all_details();
		LOGGER.info("Customer filled web form details.");
	}

	@Step
	public void complete_the_order() {

		demoBlazeIndex.complete_the_order();
		LOGGER.info("Customer clicked on \"Purchase\"");
	}

	@Step
	public void order_amount_should_be_equal_to_product_price() {

		LOGGER.info("Capturing and log purchase Id and Amount.");
		demoBlazeIndex.assertOrder();
	}

	
	@Step
	public void click_on_ok() {

		demoBlazeIndex.click_on_ok();
        LOGGER.info("Customer clicked on OK");
	}

	@Step
	public void customer_should_see(String prod) {

		demoBlazeIndex.assertProd(prod);
        LOGGER.info("Customer can see product " + prod + " from category " + this.catName );

	}

	

	
	
}
