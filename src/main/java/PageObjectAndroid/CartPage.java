package PageObjectAndroid;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class CartPage extends AndroidActions{
	
	AndroidDriver driver; // local variable refer with this keyword
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productCount;
	
	@AndroidFindBy(id= "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalProductPrice;

	public CartPage(AndroidDriver driver) {
		super(driver); // super to call parent class constructor and sending driver;
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); // this is for page factory
	}
	
	public double sum=0;
	public double totalAmountNum=0;
	
	public void totalPriceValidation() {
		SoftAssert softassert= new SoftAssert();
		double sum=0;
		for(int i=0;i<productCount.size();i++) {
			String productPrice= productCount.get(i).getText().substring(1);
			double numericPrice= Double.parseDouble(productPrice);
			sum= sum+numericPrice;
		}
		System.out.println(sum);
		String totalAmount= totalProductPrice.getText();
		String numericTotalAmountValue= totalAmount.substring(1);
		totalAmountNum= Double.parseDouble(numericTotalAmountValue);
		
	}

}
