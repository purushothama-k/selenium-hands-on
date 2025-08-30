package selenium.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.pageobjects.CartPage;
import selenium.pageobjects.CheckoutPage;
import selenium.pageobjects.ConfirmationPage;
import selenium.pageobjects.LandingPage;
import selenium.pageobjects.ProductCatalog;
import selenium.testComponents.BaseTest;

public class StandAloneTest extends BaseTest {

  @Test
  public void submitOrder() throws InterruptedException {
    String productName = "ZARA COAT 3";

    LandingPage landingPage = launchApplication();
    System.out.println("Navigated to: " + landingPage.URL);
    ProductCatalog productCatalog = landingPage.login(
      "tedex65272@luxpolar.com",
      "Password12345$"
    );

    // Product Catalog
    List<WebElement> products = productCatalog.getProductsList();
    System.out.println("Total products found: " + products.size());
    System.out.println("Searching for product: " + productName);

    WebElement product = productCatalog.getProductByName(productName);
    System.out.println("Product found: " + (product != null));

    productCatalog.addProductToCart(productName);

    CartPage cartPage = productCatalog.goToCartPage();
    Boolean match = cartPage.verifyProductDisplay(productName);
    Assert.assertTrue(match, "Product not found in cart: " + productName);

    CheckoutPage checkoutPage = cartPage.goToCheckout();
    checkoutPage.selectCountry();

    ConfirmationPage confirmationPage = checkoutPage.submitOrder();
    String confirmMessage = confirmationPage.getConfirmationMessage();
    Assert.assertTrue(
      confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."),
      "Order not placed successfully"
    );
  }
}
