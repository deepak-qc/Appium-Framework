package E_comm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import PageObjectAndroid.CartPage;
import PageObjectAndroid.FormPage;
import PageObjectAndroid.ProductCataloguePage;

public class E_Comm1 extends SetUpEComm {
	FormPage formpage = new FormPage(driver);

	@BeforeMethod
	public void preSetUp() {
		formpage.setActivity();
	}

	@Test(dataProvider = "getData")
	public void FillForm(HashMap<String, String> input) throws InterruptedException { // com.androidsample.generalstore:id/nameField
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.id("com.androidsample.generalstore:id/nameField")));
		formpage.setNameField(input.get("name"));
		formpage.setGender(input.get("gender"));
		formpage.setCountrySelection(input.get("country"));

		ProductCataloguePage pcp = formpage.letShopButton();
		// product page
		pcp = new ProductCataloguePage(driver);
		pcp.addItemToCartByIndex(0);
		pcp.addItemToCartByIndex(1);
		CartPage cartPage = pcp.cartButtonClick();

		Assert.assertEquals(cartPage.sum, cartPage.totalAmountNum);
//	
	}

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		// convert json file content to json string
		/*
		 * this methord will read the file and convert into jsonstring --> this
		 * .readFileToString methord comes under FileUtils class and its comes under
		 * commons-io package.
		 */
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\E_comm\\ecomm.json"),
				StandardCharsets.UTF_8);
		// here ObjectMapper class is use to convert json content into list of HashMap.
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir") + "\\src\\test\\java\\E_comm\\ecomm.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	//key- name , value- Deepak Singh
	//{ {hash}, {hash}   } data
}
