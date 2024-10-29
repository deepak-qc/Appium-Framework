package utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions  extends AppiumUtils{
	
	AndroidDriver driver;
	public AndroidActions(AndroidDriver driver) {
		super(driver);
		this.driver= driver;
	}
	

	public void scrollToText(String text) {
		driver.findElement(
				AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))"));
		
	}
	}
