package selenium.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.pageobjects.LandingPage;

public class BaseTest {

  protected WebDriver driver;
  private static Properties properties = new Properties();

  static {
    try {
      FileInputStream file = new FileInputStream(
        System.getProperty("user.dir") +
        "/src/main/java/selenium/resources/GlobalData.properties"
      );
      properties.load(file);
    } catch (IOException e) {
      e.printStackTrace();
      // fallback defaults
      properties.setProperty("browser", "chrome");
      properties.setProperty("url", "https://rahulshettyacademy.com/client");
    }
  }

  public static String getProperty(String property) {
    return properties.getProperty(property);
  }

  public WebDriver initializeDriver() {
    String browserName = getProperty("browser");

    if (browserName.equalsIgnoreCase("chrome")) {
      driver = new ChromeDriver();
    } else if (browserName.equalsIgnoreCase("firefox")) {
      driver = new FirefoxDriver();
    } else if (browserName.equalsIgnoreCase("edge")) {
      driver = new EdgeDriver();
    } else {
      throw new RuntimeException("Browser not supported: " + browserName);
    }

    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

    return driver;
  }

  public LandingPage launchApplication() {
    driver = initializeDriver();
    LandingPage landingPage = new LandingPage(driver);
    landingPage.goTo();
    return landingPage;
  }
}
