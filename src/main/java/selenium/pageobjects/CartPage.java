package selenium.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

  WebDriver driver;

  public CartPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(css = ".cartSection h3")
  List<WebElement> productTitles;

  @FindBy(css = ".totalRow button")
  WebElement checkoutButton;

  public Boolean verifyProductDisplay(String productName) {
    Boolean isProductDisplayed = productTitles
      .stream()
      .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    return isProductDisplayed;
  }

  public CheckoutPage goToCheckout() {
    checkoutButton.click();
    return new CheckoutPage(driver);
  }
}
