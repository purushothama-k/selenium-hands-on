package selenium.testComponents;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

  WebDriver driver;

  public static Properties properties;

  static {
    try {
      FileInputStream file = new FileInputStream(
        System.getProperty("user.dir") +
        "src\\main\\java\\selenium\\resources\\GlobalData.properties"
      );
      properties = new Properties();
      properties.load(file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String getProperty(String browser) {
    return properties.getProperty(browser);
  }

  public void initializeDriver() {
    String browserName = getProperty("browser");

    if (browserName.equalsIgnoreCase("chrome")) {
      driver = new ChromeDriver();
    } else if (browserName.equalsIgnoreCase("firefox")) {
      driver = new FirefoxDriver();
    } else if (browserName.equalsIgnoreCase("edge")) {
      driver = new EdgeDriver();
    }

    driver.manage().window().maximize();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
  }

  public void launchApplication() {
    driver.get(getProperty("url"));
  }
}
