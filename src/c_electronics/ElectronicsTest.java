package c_electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.Random;

public class ElectronicsTest extends Utility {


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
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully(){
//        1.1 Mouse Hover on “Electronics”Tab
//        1.2 Mouse Hover on “Cell phones” and click
        mouseHoverOnElement(By.linkText("Electronics"));
        mouseClickOnElement(By.xpath("(//a[contains(text(), 'Cell phones')])[1]"));

//        1.3 Verify the text “Cell phones”
        String expectedTextCellPhone = "Cell phones";
        String actualTextCellPhone = getTextFromElement(By.xpath("//h1[text()='Cell phones']"));
        Assert.assertEquals("User is not on cell phones page", expectedTextCellPhone, actualTextCellPhone);

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

//        2.1 Mouse Hover on “Electronics”Tab
        mouseHoverOnElement(By.linkText("Electronics"));

//        2.2 Mouse Hover on “Cell phones” and click
        mouseClickOnElement(By.xpath("(//a[contains(text(), 'Cell phones')])[1]"));

//        2.3 Verify the text “Cell phones”
        String expectedTextCellPhone = "Cell phones";
        String actualTextCellPhone = getTextFromElement(By.xpath("//h1[text()='Cell phones']"));
        Assert.assertEquals("User is not on cell phones page", expectedTextCellPhone, actualTextCellPhone);

//        2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));

        Thread.sleep(5000);
//        2.5 Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"));

//        2.6 Verify the text “Nokia Lumia 1020”
        String expectedTextNokia = "Nokia Lumia 1020";
        String actualTextNokia = getTextFromElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));
        Assert.assertEquals("User is not in Nokia product", expectedTextNokia, actualTextNokia);

//        2.7 Verify the price “$349.00”
        String expectedNokiaPrice = "$349.00";
        String actualNokiaPrice = getTextFromElement(By.xpath("//span[@id='price-value-20' and text()=' $349.00 ']"));
        Assert.assertEquals("That is not price you wish", expectedNokiaPrice, actualNokiaPrice);

//        2.8 Change quantity to 2
        clearContent(By.id("product_enteredQuantity_20"));
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");

//        2.9 Click on “ADD TO CART” tab
        clickOnElement(By.id("add-to-cart-button-20"));

//        2.10 Verify the Message "The product has been added to your shopping cart" on Top
        String expectedTextAddToCartMessage = "The product has been added to your shopping cart";
        String actualTextAddToCartMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        Assert.assertEquals("Product is not added yet", expectedTextAddToCartMessage, actualTextAddToCartMessage);

//        green Bar After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class='close']"));

//        2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        WebElement goToCart = driver.findElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        goToCart.click();

//        2.12 Verify the message "Shopping cart"
        String expectedTextShoppingCart = "Shopping cart";
        String actualTextShoppingCart = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("User is not in shopping cart, please press shopping cart and go to cart button", expectedTextShoppingCart, actualTextShoppingCart);

//        2.13 Verify the quantity is 2
        String expectedQty = "2";
        String actualQty = getTextFromElement(By.xpath("//span[contains(text(),'(2)')]"));
        Assert.assertEquals("Quantity must be 2", expectedQty, actualQty.substring(1,2));

        Thread.sleep(6000);
//        2.14 Verify the Total $698.00
        String expectedTextTotalValue = "$698.00";
        String actualTextTotalValue = getTextFromElement(By.xpath("(//strong[text()='$698.00'])[2]"));
        Assert.assertEquals("Quantity is not updated, please check", expectedTextTotalValue, actualTextTotalValue);

//        2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

//        2.16 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

//        2.17 Verify the Text “Welcome, Please Sign In!”
        String expectedTextWelcomToSignIn = "Welcome, Please Sign In!";
        String actualTextWelcomToSignIn = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        Assert.assertEquals("User is not on sign in page", expectedTextWelcomToSignIn, actualTextWelcomToSignIn);

//        2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));

//        2.19 Verify the text “Register”
        String expectedTextRegister = "Register";
        String actualTextRegister = getTextFromElement(By.xpath("//h1[text()='Register']"));
        Assert.assertEquals("User is not on register page", expectedTextRegister, actualTextRegister);

//        2.20 Fill the mandatory fields
        clickOnElement(By.id("gender-male"));

        String firstName = "Prime";
        String lastName = "Testing";
        sendTextToElement(By.id("FirstName"), firstName); //first name
        sendTextToElement(By.id("LastName"), lastName); //first name

        selectByValueFromDropDown(By.name("DateOfBirthDay"), "16");
        selectByValueFromDropDown(By.name("DateOfBirthMonth"), "12");
        selectByValueFromDropDown(By.name("DateOfBirthYear"), "1965");

        Random randomGenerator = new Random();
        int emailNum = randomGenerator.nextInt(9999);
        sendTextToElement(By.id("Email"), firstName + "." + lastName + emailNum + "@email.com");

        int passNum = randomGenerator.nextInt(999);
        sendTextToElement(By.id("Password"), lastName + "#" + passNum);
        sendTextToElement(By.id("ConfirmPassword"), lastName + "#" + passNum);

//        2.21 Click on “REGISTER” Button
        clickOnElement(By.id("register-button"));

//        2.22 Verify the message “Your registration completed”
        String expectedTextRegistrationComplete = "Your registration completed";
        String actualTextRegistrationComplete = getTextFromElement(By.xpath("//div[@class='result' and text()='Your registration completed']"));
        assertAssert("Registration unsucessful",expectedTextRegistrationComplete, actualTextRegistrationComplete);

//        2.23 Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[contains(@class, 'continue-button')]"));

//        2.24 Verify the text “Shopping card”
        String expectedTextCart = "Shopping cart";
        String actualTextCart = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        assertAssert("User is not in Shopping cart", expectedTextCart, actualTextCart);

//        2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

//        2.26 Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));

//        2.27 Fill the Mandatory fields
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "133");
        sendTextToElement(By.id("BillingNewAddress_City"), "Ahmedabad");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "999 Raghunath Ni Pol");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "380011");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "9989711425");

//        2.28 Click on “CONTINUE”
        clickOnElement(By.name("save"));

//        2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_2"));

//        2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));

//        2.31 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));

//        2.32 Select “Visa” From Select credit card dropdown
        selectByValueFromDropDown(By.id("CreditCardType"), "visa");

//        2.33 Fill all the details
        sendTextToElement(By.id("CardholderName"), firstName + " " + lastName);
        sendTextToElement(By.id("CardNumber"), "4024007105297690");
        selectByValueFromDropDown(By.id("ExpireMonth"), "10");
        selectByValueFromDropDown(By.id("ExpireYear"), "2023");
        sendTextToElement(By.id("CardCode"), "728");

//        2.34 Click on “CONTINUE”
        clickOnElement(By.id("payment-info-buttons-container"));

//        2.35 Verify “Payment Method” is “Credit Card”
        String expectedPaymentMethod1 = "Payment Method:";
        String actualTextPaymentMethod1 = getTextFromElement(By.xpath("//span[contains(text(), 'Payment Method:')]"));
        assertAssert("This is not payment method", expectedPaymentMethod1, actualTextPaymentMethod1);

        String expectedPaymentMethod = "Credit Card";
        String actualPaymentMethod = getTextFromElement(By.xpath("//span[contains(text(), 'Credit Card')]"));
        assertAssert("Your payment is not matching", expectedPaymentMethod, actualPaymentMethod);

//        2.36 Verify “Shipping Method” is “2nd Day Air”
        String expectedShipplingMethod1 = "Shipping Method:";
        String actualShippingMethod1 = getTextFromElement(By.xpath("//span[contains(text(), 'Shipping Method:')]"));
        assertAssert("This is not shipping method", expectedShipplingMethod1, actualShippingMethod1);

        String expectedShipplingMethod = "2nd Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//span[contains(text(), '2nd Day Air')]"));
        assertAssert("Your shipping is not matching", expectedShipplingMethod, actualShippingMethod);


//        2.37 Verify Total is “$698.00”
        String expectedFinalValue = "$698.00";
        String actualFinalValue = getTextFromElement(By.xpath("(//strong[text()='$698.00'])[2]"));
        assertAssert("Final Value is not matching", expectedFinalValue, actualFinalValue);

//        2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));

//        2.39 Verify the Text “Thank You”
        String expectedSucessMessage = "Thank you";
        String actualSucessMessage = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        assertAssert("Order is not complete", expectedSucessMessage, actualSucessMessage);

//        2.40 Verify the message “Your order has been successfully processed!”
        String expectedTextOrderProcessed = "Your order has been successfully processed!";
        String actualTextOrderProcessed = getTextFromElement(By.xpath("//strong[contains(text(), 'fully processed!')]"));
        Assert.assertEquals("Order unsucessfull", expectedTextOrderProcessed, actualTextOrderProcessed);

//        2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));

//        2.42 Verify the text “Welcome to our store”
        String expectedTextWelcome = "Welcome to our store";
        String actualTextWelcome = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        assertAssert("Not prompted to homepage", expectedTextWelcome, actualTextWelcome);

//        2.43 Click on “Logout” link
        clickOnElement(By.linkText("Log out"));

//        2.44 Verify the URL is “https://demo.nopcommerce.com/"
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://demo.nopcommerce.com/";
        assertAssert("URL is not the same", expectedUrl, currentUrl);

    }
}
