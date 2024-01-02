

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pageObject.FaqPage;
import pageObject.OrderPage;

import java.util.concurrent.TimeUnit;
@RunWith(Parameterized.class)
public class OrderTest {
   private final String name;
    private final String secondName;
    private final String address;
    private final int metroStations;
   private final String phoneNumber;



    public OrderTest(String name, String secondName, String address,
                     int metroStations, String phoneNumber) {
       this.name = name;
       this.secondName = secondName;
       this.address = address;
       this.metroStations = metroStations;
       this.phoneNumber = phoneNumber;
   }
    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] testDataSet() {
        return new Object[][]{
                {"Денис","Петров","Подольских курсантов д. 8",7,"89770000098"},
                {"Мария","Иванова","Артековская д.1",98,"+79770000000"},
        };
    }

    WebDriver driver;
    @Test
    public void orderPageTest() {
        FaqPage faqPage = new FaqPage(driver);
        faqPage.acceptCookies();
        faqPage.clickUpperOrder();
        OrderPage orderPage = new OrderPage(driver);

//        заполнение формы заказа

        //первая страница
        orderPage.inputName(name);
        orderPage.inputSecondName(secondName);
        orderPage.inputAddress(address);
        orderPage.chooseMetroStation(metroStations);
        orderPage.inputPhoneNumber(phoneNumber);
        orderPage.clickNextButton();

        //вторая страница
        orderPage.setDate("05.06.2023");
        orderPage.chooseDuration(0);
        orderPage.chooseCheckBox(1);
        orderPage.writeComment("Не звонить в домофон");
        orderPage.clickNextButton();

//        проверка окна подтверждения заказа
        orderPage.clickYesButton();
        Assert.assertTrue("Не отображается подтверждение",
                orderPage.isAppearOrderConfirmed());
    }
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void quit(){

        driver.quit();
    }
}