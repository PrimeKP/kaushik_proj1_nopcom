package a_homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;


public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";
    static String menu = "Electronics";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }

    //this method clicks when parameter passed by calling in another method
    //this method clicks when parameter passed by calling in another method
    public void selectMenu(String menu){
        Actions actions = new Actions(driver);
        WebElement clickMenu = driver.findElement(By.linkText(menu));
        actions.moveToElement(clickMenu).click().build().perform();

    }
    //this test verifies that parameter passed clicked and then verified that we are in correct page
    @Test
    public void verifyPageNavigation(){
        selectMenu(menu); //pass parameters here
        String expectedPageText = menu;
        String actualPageText = getTextFromElement(By.xpath("//h1[contains(text()," + "'" + menu +"')]"));
        Assert.assertEquals("Not navigated to Computers page",expectedPageText, actualPageText);
    }


}
