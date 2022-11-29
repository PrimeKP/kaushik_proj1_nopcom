package b_computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.util.Random;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {

        //1.1 clicking on computer and
        //1.2 then on desktop option
        Actions actions = new Actions(driver);
        WebElement computer = driver.findElement(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        WebElement desktop = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        actions.moveToElement(computer).build().perform();
        actions.moveToElement(desktop).click().build().perform();

        //1.3 clicking on position Z to A
        selectByValueFromDropDown(By.id("products-orderby"), "6");

        //1.4 verifying products are sorted in Z to A order
        String expectedTextZToA = "Name: Z to A";
        String actualTextZToA = getTextFromElement(By.xpath("//select[@id='products-orderby']/option[@value='6']"));
        Assert.assertEquals("Product is not in order Z to A", expectedTextZToA, actualTextZToA);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //clicking on computer and then on desktop option
        Actions actions = new Actions(driver);
        WebElement computer = driver.findElement(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        WebElement desktop = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();

        //2.3 clicking on position A to Z
        selectByValueFromDropDown(By.id("products-orderby"), "5");

        Thread.sleep(6000);

        //2.4 clicking on add to card
        clickOnElement(By.xpath("(//button[contains(text(),'to cart')])[1]"));

        //2.5 verifying text build on computer
        String expectedTextBuildComputer = "Build your own computer";
        String actualTextBuldComputer = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        Assert.assertEquals("User in not in Build your own computer page", expectedTextBuildComputer, actualTextBuldComputer);

        //2.6 selecting "2.2 GHz Intel Pentium Dual-Core E2200"
        selectByVisibleTextFromDropDown(By.name("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7 selecting "8GB [+$60.00]"
        selectByVisibleTextFromDropDown(By.name("product_attribute_2"), "8GB [+$60.00]");

        //2.8 selecting HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));

        //2.9 selecting OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));

        //2.10 checking two boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        clickOnElement(By.id("product_attribute_5_12"));

        Thread.sleep(10000);

        //2.11 verifying the price "$1,475.00"
        String expectedTextPrice = "$1,475.00";
        String actualTextPrice = getTextFromElement(By.xpath("//span[@id='price-value-1']"));
        Assert.assertEquals("Something is not added, check you option again", expectedTextPrice, actualTextPrice);

        //2.12 Adding configuration to cart
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        //2.13 Verifying alert message that "The product has been added to your shopping cart" on Top green Bar
        //and closing the message
        String expectedTextAddToCartMessage = "The product has been added to your shopping cart";
        String actualTextAddToCartMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        Assert.assertEquals("Product is not added yet", expectedTextAddToCartMessage, actualTextAddToCartMessage);
        clickOnElement(By.xpath("//span[@class='close']"));

        //2.14 hovering on mouse on "Shopping cart" and clicking on "GO TO CART"
        mouseHoverOnElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        WebElement goToCart = driver.findElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        goToCart.click();

        //2.15 verifying message "Shopping cart"
        String expectedTextShoppingCart = "Shopping cart";
        String actualTextShoppingCart = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("User is not in shopping cart, please press shopping cart and go to cart button", expectedTextShoppingCart, actualTextShoppingCart);

        //2.16 changing the Qty to "2" and Click on "Update shopping cart"
        clearContent(By.xpath("//input[@class='qty-input']"));
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.id("updatecart"));

        //2.17 verifying the Total"$2,950.00"
        String expectedTextTotalValue = "$2,950.00";
        String actualTextTotalValue = getTextFromElement(By.xpath("(//strong[text()='$2,950.00'])[2]"));
        Assert.assertEquals("Quantity is not updated, please check", expectedTextTotalValue, actualTextTotalValue);

        //2.18 checking on box  “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

        //1.19 clicking on “CHECKOUT”
        clickOnElement(By.id("checkout"));

        //2.2 verifying the text “Welcome, Please Sign In!”
        String expectedTextWelcomToSignIn = "Welcome, Please Sign In!";
        String actualTextWelcomToSignIn = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        Assert.assertEquals("User is not on sign in page", expectedTextWelcomToSignIn, actualTextWelcomToSignIn);

        // 2.21Click on “CHECKOUT AS GUEST”Tab
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));

        // 2.22 Fill the all mandatory field

        String firstName = "Prime";
        String lastName = "Testing";
        sendTextToElement(By.id("BillingNewAddress_FirstName"), firstName); //first name
        sendTextToElement(By.id("BillingNewAddress_LastName"), lastName); //first name

        Random randomGenerator = new Random();
        int emailNum = randomGenerator.nextInt(9999);
        sendTextToElement(By.id("BillingNewAddress_Email"), firstName + "." + lastName + emailNum + "@email.com");

        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "133");
        sendTextToElement(By.id("BillingNewAddress_City"), "Ahmedabad");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "999 Raghunath Ni Pol");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "380011");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "9989711425");

        // 2.23 Click on “CONTINUE”
        clickOnElement(By.name("save"));


        // 2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));

        // 2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));

        // 2.26 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));

        // 2.27 Select “Master card” From Select credit card dropdown
        selectByValueFromDropDown(By.id("CreditCardType"), "MasterCard");

        // 2.28 Fill all the details
        sendTextToElement(By.id("CardholderName"), firstName + " " + lastName);
        sendTextToElement(By.id("CardNumber"), "5357793919621142");
        selectByValueFromDropDown(By.id("ExpireMonth"), "10");
        selectByValueFromDropDown(By.id("ExpireYear"), "2025");
        sendTextToElement(By.id("CardCode"), "283");

        // 2.29 Click on “CONTINUE”
        clickOnElement(By.id("payment-info-buttons-container"));

        // 2.30 Verify “Payment Method” is “Credit Card”

        String expectedPaymentMethod1 = "Payment Method:";
        String actualTextPaymentMethod1 = getTextFromElement(By.xpath("//span[contains(text(), 'Payment Method:')]"));
        Assert.assertEquals("This is not payment method", expectedPaymentMethod1, actualTextPaymentMethod1);

        String expectedPaymentMethod = "Credit Card";
        String actualPaymentMethod = getTextFromElement(By.xpath("//span[contains(text(), 'Credit Card')]"));
        Assert.assertEquals("Your payment is not matching", expectedPaymentMethod, actualPaymentMethod);

        // 2.32 Verify “Shipping Method” is “Next Day Air”
        String expectedShipplingMethod1 = "Shipping Method:";
        String actualShippingMethod1 = getTextFromElement(By.xpath("//span[contains(text(), 'Shipping Method:')]"));
        Assert.assertEquals("This is not shipping method", expectedShipplingMethod1, actualShippingMethod1);

        String expectedShipplingMethod = "Next Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//span[contains(text(), 'Next Day Air')]"));
        Assert.assertEquals("Your shipping is not matching", expectedShipplingMethod, actualShippingMethod);

        // 2.33 Verify Total is “$2,950.00”
        String expectedFinalValue = "$2,950.00";
        String actualFinalValue = getTextFromElement(By.xpath("(//strong[text()='$2,950.00'])[2]"));
        Assert.assertEquals("Final Value is not matching", expectedFinalValue, actualFinalValue);

        // 2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));


        // 2.35 Verify the Text “Thank You”
        String expectedSucessMessage = "Thank you";
        String actualSucessMessage = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        Assert.assertEquals("Order is not complete", expectedSucessMessage, actualSucessMessage);

        // 2.36 Verify the message “Your order has been successfully processed!”
        String expectedTextOrderProcessed = "Your order has been successfully processed!";
        String actualTextOrderProcessed = getTextFromElement(By.xpath("//strong[contains(text(), 'fully processed!')]"));
        Assert.assertEquals("Order unsucessfull", expectedTextOrderProcessed, actualTextOrderProcessed);

        // 2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));

        // 2.38 Verify the text “Welcome to our store”
        String expectedTextWelcome = "Welcome to our store";
        String actualTextWelcome = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        Assert.assertEquals("Not prompted to homepage", expectedTextWelcome, actualTextWelcome);


    }
   /* @After
    @Override
    public void closeBrowser() {
        super.closeBrowser();
    }*/
}
