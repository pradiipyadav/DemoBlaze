package serenity.serenity_pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.demoblaze.com/index.html")
public class DemoBlazeIndex extends PageObject {

	@FindBy(css =".list-group>a#itemc")
	List<WebElementFacade> categories;
	
	@FindBy(css ="h4.card-title>a")
	List<WebElementFacade> result;
	
	@FindBy(xpath = "//tr[@class='success']/td[3]")
	WebElementFacade price;
	
	String laptop = "(//a[contains(text(),'{0}')])[1]";
	
	By phone = By.xpath("(//p[contains(text(),'Qualcomm')])[1]");
	
	@FindBy(xpath="//a[text()='Add to cart']")
	WebElementFacade addToCart;
	
	@FindBy(xpath="//a[text()='Cart']")
	WebElementFacade cart;
	
	@FindBy(xpath="//a[normalize-space(text())='Home']")
	WebElementFacade homeBtn;
	
	String delete = "//tr[@class='success']/td[text()='{0}']/following-sibling::td[2]/a";
	
	@FindBy(xpath = "//button[@data-target='#orderModal']")
	WebElementFacade placeOrderBtn;
	
	@FindBy(css = "input#name")
	WebElementFacade nameInput;
	
	@FindBy(css = "input#country")
	WebElementFacade countryInput;
	
	@FindBy(css = "input#city")
	WebElementFacade cityInput;
	
	@FindBy(css = "input#card")
	WebElementFacade cardInput;
	
	@FindBy(css = "input#month")
	WebElementFacade monthInput;
	
	@FindBy(css = "input#year")
	WebElementFacade yearInput;
	
	@FindBy(xpath="//button[text()='Purchase']")
	WebElementFacade purchaseBtn;
	
	@FindBy(xpath = "//p[@class='lead text-muted ']")
	WebElementFacade orderData;
	
	@FindBy(css = "div.sa-confirm-button-container>button")
	WebElementFacade okBtn;
	
	String prodPrice, catName, product;

    private static final Logger LOGGER = LogManager.getLogger();

	private String amount;

	private String id;
	
	public void nav(String url) {
		open();
	}

	public void catName(String catName) {
		
		this.catName = catName;
		waitFor(ExpectedConditions.numberOfElementsToBeMoreThan(phone, 0));
		categories.forEach(w -> {

			if(w.getText().equalsIgnoreCase(catName)) {
				w.click();	
				}
		});
		
	}
	
	public void addToCart(String product) {
		
		waitFor(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(laptop.replaceAll("\\{0\\}", product)), 0));
		try {
		findBy(laptop, product).click();
		}catch (StaleElementReferenceException e) {
			findBy(laptop, product).click();
		}
		
		addToCart.waitUntilClickable().click();
		waitFor(ExpectedConditions.alertIsPresent());
		getDriver().switchTo().alert().accept();

	}

	public void add_to_cart_second_product(String product) {

		homeBtn.waitUntilClickable().click();
		catName(this.catName);
		addToCart(product);
		
		
	}
	

	public void deleteItem(String prod) {
		cart.click();
		findBy(delete, prod).click();
		findBy(delete, prod).waitUntilNotVisible();
		this.prodPrice = price.waitUntilVisible().getText();

	}

	public void proceed_for_place_order() {

		placeOrderBtn.waitUntilClickable().click();
	}

	public void fill_all_details() {

		nameInput.type("XYZ");
		countryInput.click();
		countryInput.type("XYZ");
		cityInput.type("XYZ");
		cardInput.type("222222222222");
		monthInput.type("02");
		yearInput.type("2020");
		//We can also generate random details

	}

	public void complete_the_order() {

		purchaseBtn.click();
	}

	public void assertOrder() {

		System.out.println(orderData.getText());
		String orderData = this.orderData.getText();
		Pattern patternId = Pattern.compile(".*Id:\\s(\\d+).*");
		Pattern patternAmount = Pattern.compile(".*Amount:\\s(\\d+).*");
        Matcher matchedId = patternId.matcher(orderData);
        Matcher matchedAmount = patternAmount.matcher(orderData);

        while(matchedAmount.find()) {
        	amount = matchedAmount.group(1);
        	System.out.println("Amount "+ amount);
        }
        
        while(matchedId.find()) {
        	id = matchedId.group(1);
        	System.out.println("Id "+ id);
        }
        
        LOGGER.info("Order Amount "+ amount);
        LOGGER.info("Order Id " + id);

        Assert.assertEquals(prodPrice, amount);
        
        
	}

	public void click_on_ok() {

		okBtn.waitUntilClickable().click();
	}
	
	

	
}
