package selenium;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pageobjects.LandingPage;
import selenium.pageobjects.ProductCatalog;

public class StandAloneTest {

  public static void main(String[] args) throws InterruptedException {
    String productName = "ZARA COAT 3";
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

    // Landing Page
    LandingPage landingPage = new LandingPage(driver);
    landingPage.goTo();
    System.out.println("Navigated to: " + landingPage.URL);
    landingPage.login("tedex65272@luxpolar.com", "Password12345$");

    // Product Catalog
    ProductCatalog productCatalog = new ProductCatalog(driver);
    List<WebElement> products = productCatalog.getProductsList();
    System.out.println("Total products found: " + products.size());
    System.out.println("Searching for product: " + productName);
    WebElement product = productCatalog.getProductByName(productName);
    System.out.println("Product found: " + (product != null));

    productCatalog.addProductToCart(productName);
    productCatalog.goToCart();
  }
}
