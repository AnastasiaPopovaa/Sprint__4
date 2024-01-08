package pageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//https://qa-scooter.praktikum-services.ru/order

public class OrderPage {
    //Локаторы
    WebDriver driver;
    public OrderPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final By orderHeader = By.className("Order_Header__BZXOb");
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By secondName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStation = By.className("select-search__input"); // стандция метро
    private final By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By WhenToBringDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By duration = By.className("Dropdown-arrow");
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By confirmYesButton = By.xpath(".//*[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    private final By orderConfirmed = By.className("Order_Text__2broi");
    @FindBy(className = "Checkbox_Input__14A2w")
    private List<WebElement> checkBox;

    //Методы

    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }
    public void inputName(String text){//    заполнить имя
        driver.findElement(name).sendKeys(text);
    }
    public void inputSecondName(String text){//    заполнить Фамилию
        driver.findElement(secondName).sendKeys(text);
    }
    public void inputAddress(String text){//    заполнить адрес
        driver.findElement(address).sendKeys(text);
    }
    public void inputPhoneNumber(String text){//    заполнить номер
        driver.findElement(phoneNumber).sendKeys(text);
    }
    public void chooseMetroStation(int n){//    заполнить номер
        driver.findElement(metroStation).click();
        driver.findElement(By.xpath(".//li[@class='select-search__row' and @data-index='"+n+"']")).click();//n
    }
    public void setDate(String date){ //выбрать дату доставки
        driver.findElement(WhenToBringDate).sendKeys(date);
    }
    public void chooseDuration(int n){
        driver.findElement(duration).click();
        driver.findElements(By.className("Dropdown-option")).get(n).click();
    }
    public void writeComment(String comment){
        driver.findElement(commentInput).sendKeys(comment);
    }
    public void chooseCheckBox(int i){  //выбор цвета самоката
        checkBox.get(i).click();
    }
    public void clickYesButton(){
        driver.findElement(confirmYesButton).click();
    }
    public boolean isAppearOrderConfirmed(){
        return !(driver.findElement(orderConfirmed).getText().equals(" "));
    }
    public boolean isDisplayedOrderHeader() {
        return driver.findElement(orderHeader).isDisplayed();
    }

}
