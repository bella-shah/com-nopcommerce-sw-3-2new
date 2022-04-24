package computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Utility;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void testName() {
        clickOnElement(By.xpath("//a[@href='/computers']"));
        clickOnElement(By.xpath("//img[@alt='Picture for category Desktops']"));
        selectByVisibleTextFromDropDown(By.name("products-orderby"), "Name: Z to A");
        verifyText("Name: Z to A", getTextFromElement(By.xpath("//option[contains(text(),'Name: Z to A')]")), "Error, Message not displayed");

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        clickOnElement(By.xpath("//a[@href='/computers']"));
        clickOnElement(By.xpath("//img[@alt='Picture for category Desktops']"));
        selectByVisibleTextFromDropDown(By.name("products-orderby"), "Name: Z to A");
        sendTextToElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[1]"), Keys.ENTER + "1");
        verifyText("Build your own computer", getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]")), "Error, Message not displayed");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_2']"), "8GB [+$60.00]");
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        verifyText("$1,475.00", getTextFromElement(By.xpath("//span[contains(text(),'$1,475.00')]")), "Incorrect price!");
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        verifyText("shopping cart", getTextFromElement(By.xpath("//a[contains(text(),'shopping cart')]")), "Incorrect Message");
        clickOnElement(By.className("close"));
        mouseHoverClick(By.xpath("//span[contains(text(),'Shopping cart')]"));
        verifyText("Shopping cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]")), "Incorrect page");
        sendTextToElement(By.xpath("(//input[contains(@id, 'itemquantity')])"), Keys.BACK_SPACE + "2");
        clickOnElement(By.xpath("//button[text()='Update shopping cart']"));
        verifyText("$2,950.00", getTextFromElement(By.className("product-subtotal")), "Incorrect price");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        verifyText("Welcome, Please Sign In!", getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]")), "Error");
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Bella");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Shah");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "shahbella55@gmail.com");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "159 Ealing Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "HP20 1DA");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07593876523");
        clickOnElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button[4]"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.xpath("//*[@id=\"paymentmethod_1\"]"));
        clickOnElement(By.xpath("//*[@id=\"payment-method-buttons-container\"]/button"));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Bella Shah");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5555 5555 5555 4444");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "03");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2026");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "132");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        verifyText("Credit Card", getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]")), "Incorrect payment method");
        verifyText("Next Day Air", getTextFromElement(By.xpath("//*[@id=\"shipping-methods-form\"]/ul/li[2]/div[1]') and @class='value']")), "Incorrect Shipping method");
        verifyText("$2,950.00", getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]")), "Error");
       clickOnElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/button"));
        verifyText("Thank you", getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]")), "Incorrect Message");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyText("Welcome to our store", getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]")), "Incorrect page");

    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}



