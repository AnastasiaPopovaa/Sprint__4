
import pageObject.FaqPage;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;
@RunWith(Parameterized.class)
public class FaqTest {
    WebDriver driver;
    private final int questionNumber;

    public FaqTest(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    @Parameterized.Parameters // аннотация
    public static Object[][] accordionQuestionsNum() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        FaqPage cookiePage = new FaqPage(driver);
        cookiePage.acceptCookies();

    }
    @Test
    public void faqTest(){
        FaqPage faqPage = new FaqPage(driver);
        faqPage.findFAQ();
        faqPage.clickQuestion(questionNumber);
        Assert.assertTrue(faqPage.isEnabledAnswer(questionNumber));

    }
    @After
    public void quit(){
        driver.quit();
    }
}
