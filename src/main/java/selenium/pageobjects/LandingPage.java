package selenium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

  WebDriver driver;
  public String URL = "https://rahulshettyacademy.com/client";

  public LandingPage(WebDriver driver) {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  // Page Factory

  @FindBy(id = "userEmail")
  WebElement userEmail;

  @FindBy(id = "userPassword")
  WebElement userPassword;

  @FindBy(id = "login")
  WebElement loginButton;

  public void goTo() {
    driver.get(URL);
  }

  public void login(String email, String password) {
    userEmail.sendKeys(email);
    userPassword.sendKeys(password);
    loginButton.click();
  }
}
