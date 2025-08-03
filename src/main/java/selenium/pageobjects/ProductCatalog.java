package selenium.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.abstractcomponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

  WebDriver driver;

  public ProductCatalog(WebDriver driver) {
    super(driver);
    this.driver = driver;

    PageFactory.initElements(driver, this);
  }

  // List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

  @FindBy(css = ".mb-3")
  List<WebElement> products;

  @FindBy(css = ".ng-animating")
  WebElement spinnerCloseBy;

  By productsBy = By.cssSelector(".mb-3");
  By spinnerBy = By.cssSelector(".ngx-spinner-overlay");
  By toastBy = By.id("toast-container");

  By addToCartProduct = By.cssSelector(".card-body button:last-of-type");

  public List<WebElement> getProductsList() {
    waitForElementToAppear(productsBy);
    return products;
  }

  public WebElement getProductByName(String productName) {
    WebElement product = products
      .stream()
      .filter(p -> p.findElement(By.tagName("b")).getText().equals(productName))
      .findFirst()
      .orElse(null);

    return product;
  }

  public void addProductToCart(String ProductName) throws InterruptedException {
    WebElement product = getProductByName(ProductName);
    if (product == null) {
      System.out.println("  Product not found: " + ProductName);
      return;
    }
    product.findElement(addToCartProduct).click();
    waitForElementToAppear(toastBy);
    waitFroElementToDisappear(spinnerCloseBy);

    // waitForElementToAppear(spinnerBy);

    boolean isToastVisible = driver
      .findElement(toastBy)
      .getText()
      .contains("Product added to cart");

    System.out.println("Toast message visible: " + isToastVisible);
  }

  public void goToCart() {
    driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
  }
}
