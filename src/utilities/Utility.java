package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    /**
     * this method will click on element
     */

    public void clickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }

    public void sendTextToElement(By by, String text) {
        WebElement emailField = driver.findElement(by);
        emailField.sendKeys(text);
    }

    public String getTextFromElement(By by) {
        WebElement actualTextMessageElement = driver.findElement(by);

        //will bring text from method xpath string
        return actualTextMessageElement.getText();
    }
    //this method clears content in box
    public void clearContent(By by){
        driver.findElement(by).clear();
    }
    //==============================Alert methods===================================
    //by creating this method you can call method
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    //this method will accept alert message
    public void acceptAlertMessage(){
        driver.switchTo().alert().accept();
    }

    //this method will reject alert message
    public void dismissAlertMessage(){
        driver.switchTo().alert().dismiss();
    }

    //this method will get text from alert message
    public String getTextFromAlertMessage(){
        return driver.switchTo().alert().getText();
    }

    //this method send text to alert message
    public void sendTextToAlertMessage(String text){
        driver.switchTo().alert().sendKeys(text);
    }
    //================================select class methods===============================

   //this method will select from dropdown by visible text
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDwon = driver.findElement(by);

        //select class object creation

        Select select = new Select(dropDwon);
        //select by value
        select.selectByVisibleText(text);

    }

     // This method will select the option by value in DOM options

    public void selectByValueFromDropDown(By by, String value) {
        WebElement dropDwon = driver.findElement(by);

        //select class object creation

        Select select = new Select(dropDwon);
        //select by value
        select.selectByValue(value);

    }

     // This method will select the option by index in DOM option
    public void selectByIndexDropDown(By by, int index) {
        WebElement dropDwon = driver.findElement(by);

        //select class object creation

        Select select = new Select(dropDwon);
        //select by index value
        select.selectByIndex(index);

    }

     // This method will select the option by contains text
    public void selectByContainTextDropDown(By by, String parameter) {
        WebElement dropDwon = driver.findElement(by);

        //select class object creation

        Select select = new Select(dropDwon);
        //select by contain value
        List<WebElement> list = select.getOptions();

        for(WebElement element:list){
            System.out.println(element.getText());
            if(element.getText().contains(parameter)){ //here inplace of equals we can use contains
                element.click();
                break;
            }
        }

    }
//================================mouse hovering method==============================

    //this method hover on elements
    public void mouseHoverOnElement(By by){
        Actions actions = new Actions(driver);
        WebElement anyElement = driver.findElement(by);
        actions.moveToElement(anyElement).build().perform();
    }
    //this method click on elements
    public void mouseClickOnElement(By by){
        Actions actions = new Actions(driver);
        WebElement anyElement = driver.findElement(by);
        actions.moveToElement(anyElement).click().build().perform();
    }

    //===================================My Other methods========================================
    //this is Assert method which compares two text
    public void assertAssert(String message, String expected, String actual){
        Assert.assertEquals("",expected, actual);
    }


}
