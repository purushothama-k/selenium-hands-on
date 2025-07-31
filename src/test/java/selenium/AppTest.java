package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AppTest {

public static void main(String[] args) {

String productName = "ZARA COAT 3";
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));

driver.get("https://rahulshettyacademy.com/client");

driver.findElement(By.id("userEmail")).sendKeys("tedex65272@luxpolar.com");
driver.findElement(By.id("userPassword")).sendKeys("Password12345$");
driver.findElement(By.id("login")).click();

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
WebElement product = products.stream().filter(p -> p.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);

if (product != null) {
System.out.println("Filtered product name: " + product.findElement(By.tagName("b")).getText());
} else {
System.out.println("Product not found.");
}

product.findElement(By.cssSelector(".card-body button:last-of-type")).click();


wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));
System.out.println("Loader is loading.");

wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
System.out.println("Loader is closed after adding the product to the cart.");

wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
boolean isit = driver.findElement(By.id("toast-container")).getText().equalsIgnoreCase("Product Added To Cart");

System.out.println(isit);

System.out.println(driver.findElement(By.id("toast-container")).getText());

driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

// Cart
List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
cartProducts.stream().map(WebElement::getText).forEach(System.out::println);

// driver.quit();
}
}