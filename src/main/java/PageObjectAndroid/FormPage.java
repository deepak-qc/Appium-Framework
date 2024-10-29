package PageObjectAndroid;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class FormPage extends AndroidActions {
	// constructor to get android driver from base test
	AndroidDriver driver; // local variable refer with this keyword

	public FormPage(AndroidDriver driver) {
		super(driver); // super to call parent class constructor and sending driver;
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); // this is for page factory
	}

	// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Deepak
	// Singh");
	@AndroidFindBy(id = ("com.androidsample.generalstore:id/nameField"))
	private WebElement nameField; /* here u make locator private so to only call action methods not locator on @Test page 
	( this is also called encapsulation because hiding locators) */

	// driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	@AndroidFindBy(id = ("com.androidsample.generalstore:id/radioFemale"))
	private WebElement femaleOption;

	@AndroidFindBy(xpath = ("//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioMale\"]"))
	private WebElement maleOption;

	@AndroidFindBy(id = ("com.androidsample.generalstore:id/spinnerCountry"))
	private WebElement countrySelection;
	
	@AndroidFindBy(className= ("android.widget.Button"))
	private WebElement letsShopButton;
	//driver.findElement(AppiumBy.className("android.widget.Button")).click();

	// create action method for them
	public void setCountrySelection(String countryName) {
		countrySelection.click();
		scrollToText(countryName);
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\""+countryName+"\")")).click();
	}

	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void setGender(String gender) {
		if (gender.contains("female")) {
			femaleOption.click();
		} else {
			maleOption.click();
		}
	}
	
	public ProductCataloguePage letShopButton() {
		letsShopButton.click();
		return new ProductCataloguePage(driver);
	}
	
	public void setActivity() {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
			    "intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
			));
	}

}
