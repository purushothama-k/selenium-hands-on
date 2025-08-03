package test.abstractcomponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

  WebDriver driver;

  public AbstractComponent(WebDriver driver) {
    this.driver = driver;
  }

  public void waitForElementToAppear(By findBy) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
  }

  public void waitFroElementToDisappear(WebElement element)
    throws InterruptedException {
    Thread.sleep(2000);
    // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // wait.until(ExpectedConditions.invisibilityOf(element));
  }
}
