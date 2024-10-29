package PageObjectAndroid;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

//GrandParent(AppiumUtils) --> AndroidActions --> ProductCataloguePage
public class ProductCataloguePage extends AndroidActions{
	
	AndroidDriver driver; // local variable refer with this keyword

	public ProductCataloguePage(AndroidDriver driver) {
		super(driver); // super to call parent class constructor and sending driver;
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); // this is for page factory
	}
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/productAddCart")
	public List<WebElement> products;
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cartButton;
	
	public void addItemToCartByIndex(int index) {
		products.get(index).click();
	}
	
	public CartPage cartButtonClick() {
		cartButton.click();
		return new CartPage(driver);
	}
	
	
}
