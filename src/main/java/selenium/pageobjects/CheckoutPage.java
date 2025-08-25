package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

  WebDriver driver;

  @FindBy(css = ".btnn.action__submit")
  WebElement submitBtn;

  @FindBy(css = ".ta-item:last-of-type")
  WebElement selectCountry;

  @FindBy(xpath = "//input[@placeholder='Select Country']")
  WebElement country;

  By countryResultBy = By.cssSelector(".ta-results");

  public CheckoutPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void selectCountry() {
    Actions actions = new Actions(driver);
    actions.sendKeys(country, "india").build().perform();

    waitForElementToAppear(countryResultBy);

    selectCountry.click();
  }

  public ConfirmationPage submitOrder() {
    submitBtn.click();
    return new ConfirmationPage(driver);
  }
}
