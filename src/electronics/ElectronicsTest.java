package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Utility;

import java.util.Random;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        mouseHoverOnly(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Electronics ']"));
        mouseHoverClick(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));
        verifyText("Cell phones", getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]")), "Incorrect Page2");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));
        sendTextToElement(By.xpath("//div[@class='item-grid']//a[contains(text(),'Nokia Lumia 1020')]"), Keys.ENTER + "1");
        verifyText("$349.00", getTextFromElement(By.xpath("//span[contains(text(),' $349.00 ')]")), "Incorrect Price");
        sendTextToElement(By.xpath("//input[@id='product_enteredQuantity_20']"), Keys.BACK_SPACE + "2");
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));
        verifyText("shopping cart", getTextFromElement(By.xpath("//a[contains(text(),'shopping cart')]")), "Error");
        clickOnElement(By.xpath("//span[@title='Close']"));
        mouseHoverOnly(By.xpath("//span[contains(text(),'Shopping cart')]"));
        mouseHoverClick(By.xpath("//button[contains(text(),'Go to cart')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Shopping cart not found");
        verifyText("(2)", getTextFromElement(By.xpath("//span[contains(text(),'(2)')]")), "Incorrect Quantity");
        verifyText("$698.00", getTextFromElement(By.xpath("//span[contains(text(),'$698.00') and @class='product-subtotal']")), "Error");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        verifyText("Welcome, Please Sign In!", getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));
        verifyText("Register", getTextFromElement(By.xpath("//h1[contains(text(),'Register')]")), "Error");
        clickOnElement(By.xpath("//input[@id='gender-female']"));
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "Bella");
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Shah");
        clickOnElement(By.id("Email"));
        Random randomEmail = new Random();
        int randomInt = randomEmail.nextInt(1000);
        sendTextToElement(By.xpath("//input[@id='Email']"), "username" + randomInt + "@gmail.com");
        sendTextToElement(By.id("Password"), "Bella123");
        sendTextToElement(By.id("ConfirmPassword"), "Bella123");
        clickOnElement(By.xpath("//button[@id='register-button']"));
        verifyText("Your registration completed", getTextFromElement(By.xpath("//div[contains(text(),'Your registration completed')]")), "Registration failed");
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Error");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "159 Ealing Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "HP20 1DA");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07565432789");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.xpath("//input[@id='shippingoption_2']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Visa");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Shilp Patel");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "1234 5678 8976 9988");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "04");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2027");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "345");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        verifyText("Credit Card", getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")), "Incorrect payment method");
        verifyText("2nd Day Air", getTextFromElement(By.xpath("//span[contains(.,'2nd Day Air')]")), "Error");
        verifyText("$698.00", getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        verifyText("Thank you", getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")), "Error");
        verifyText("Your order has been successfully processed!", getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyText("Welcome to our store", getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")), "Error");
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://demo.nopcommerce.com/");
    }
    @After
    public void closeDown(){
        closeBrowser();
    }

}
