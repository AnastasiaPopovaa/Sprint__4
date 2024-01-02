import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.*;
import java.util.concurrent.TimeUnit;

public class LowerButtonTest {
    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void lowerOrderButtonTest(){
        FaqPage faqPage = new FaqPage(driver);
        faqPage.acceptCookies();
        faqPage.clickLowerOrder();
        OrderPage orderPage = new OrderPage(driver);

        Assert.assertTrue(orderPage.isDisplayedOrderHeader());
    }
    @After
    public void quit(){
        driver.quit();
    }
}