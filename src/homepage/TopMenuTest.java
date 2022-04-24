package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyPageNavigation(){
        selectMenu("Computers");
        String actTab = getTextFromElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        System.out.println("Actual Tab Name is:" +actTab);
        messageValidation("Computers",actTab);
    }
    @After
    public void closeDown(){
        closeBrowser();
    }
}










