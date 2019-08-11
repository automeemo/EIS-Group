import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Booking {

    private WebDriver driver = new ChromeDriver();

    @Test
    public void main() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/marina/IdeaProjects/Booking/chromedriver");
        navigateToSortedList();
        List<WebElement> prices = driver.findElements(By.cssSelector(".bui-price-display__value.prco-inline-block-maker-helper"));
        int firstPrice = intFromPrice(prices.get(0).getText());
        int secondPrice = intFromPrice(prices.get(1).getText());
        Assert.assertTrue(firstPrice < secondPrice);
        Thread.sleep(5000);
        driver.quit();
    }
    private void navigateToSortedList() throws InterruptedException {
        String CITY = "ss";
        String LONDON_IN_SS = "[data-label=\"London, Greater London, United Kingdom\"]";
        String CHECK_IN_DATE = "[data-date=\"2019-09-09\"]";
        String CHECK_OUT_DATE = "[data-date=\"2019-09-16\"]";
        String VISITORS = "xp__guests__toggle";
        String SEARCH = ".sb-searchbox-submit-col.-submit-button";
        String SORT_BY_PRICE = "[data-category=\"price\"]";

        driver.get("https://www.booking.com");
        Thread.sleep(1000);
        driver.findElement(By.id(CITY)).sendKeys("London");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(LONDON_IN_SS)).click();
        driver.findElement(By.cssSelector(CHECK_IN_DATE)).click();
        driver.findElement(By.cssSelector(CHECK_OUT_DATE)).click();
        driver.findElement(By.id(VISITORS)).click();
        driver.findElement(By.cssSelector(SEARCH)).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(SORT_BY_PRICE)).click();
        Thread.sleep(5000);
    }

    private int intFromPrice(String price) {
        return Integer.parseInt(price.replaceAll(" ","").replaceAll("UAH",""));
    }
}