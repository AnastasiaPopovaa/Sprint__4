package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FaqPage {

    WebDriver driver;
    public FaqPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //надпись "вопросы о важном"
    private final By headerImportantQuestions = By.xpath(".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном'] ");

   //верхняя кнопка заказать
    private final By upperOrderButton = By.className("Button_Button__ra12g");

    // нижняя кнопка заказать
    private final By lowerOrderButton = By.className("Home_FinishButton__1_cWm");
    // кнопка да все привыкли
    private final By AcceptCookieButton = By.id("rcc-confirm-button");

    private final String[]expectedText = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
    };

    //Методы:
    // клик на вопрос
    public void clickQuestion(int n){
        By question = By.id("accordion__heading-" + n);
        driver.findElement(question).click();
    }
    //проверить открытие текста
    public boolean isEnabledAnswer(int n){
        By element =  By.xpath(".//*[@id='accordion__panel-"+n+"']/p");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
        String actualText = driver.findElement(element).getText();
        boolean result = actualText.equals(expectedText[n]);
        return result;
    }

    //нажать на кнопку "заказать"
    public void clickUpperOrder (){
        driver.findElement(upperOrderButton).click();
    }
    public void clickLowerOrder (){
        driver.findElement(lowerOrderButton).click();
    }

    //скролл до FAQ
    public void findFAQ(){
        WebElement element = driver.findElement(headerImportantQuestions);
        ((JavascriptExecutor)
            driver).executeScript("arguments[0].scrollIntoView();", element);
}
    public void acceptCookies(){
        driver.findElement(AcceptCookieButton).click();
    }


}