package serenity.serenity_steps;

import net.thucydides.core.annotations.Step;
import serenity.serenity_pages.DemoBlazeIndex;

public class DemoBlazeSteps {

	DemoBlazeIndex demoBlazeIndex;
	
	@Step
	public void nav(String url) {
		demoBlazeIndex.nav(url);
	}
	
	@Step
	public void catNav(String catName) {
		demoBlazeIndex.catName(catName);
	}

	@Step
	public void addToCart(String product) {
		demoBlazeIndex.addToCart(product);
	}

	@Step
	public void add_to_cart_second_product(String product) {
		
		demoBlazeIndex.add_to_cart_second_product(product);

	}

	@Step
	public void deleteItem(String prod) {
		
		demoBlazeIndex.deleteItem(prod);
	}

	@Step
	public void proceed_for_place_order() {
		
		demoBlazeIndex.proceed_for_place_order();
	}

	@Step
	public void fill_all_details() {

		demoBlazeIndex.fill_all_details();
	}

	@Step
	public void complete_the_order() {

		demoBlazeIndex.complete_the_order();
	}

	@Step
	public void order_amount_should_be_equal_to_product_price() {

		demoBlazeIndex.assertOrder();
	}

	
	@Step
	public void click_on_ok() {

		demoBlazeIndex.click_on_ok();
	}

	

	
	
}
