package ModernTester;

import TestBaseFolder.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class IFrameTest extends TestBase {

    static final Logger logger = LoggerFactory.getLogger(ModernTester.IFrameTest.class);
    @Test
    void TestIframe() throws Exception {
        getDriver().get("https://seleniumui.moderntester.pl/iframes.php");
        List<WebElement> frame = getDriver().findElements(By.tagName("iframe"));

        for (WebElement f : frame) {

            getDriver().switchTo().frame(f.findElement(By.xpath("//iframe[@name='iframe1']")));

            WebElement firstname = getDriver().findElement(By.xpath("//input[@id='inputFirstName3']"));
            WebElement surname = getDriver().findElement(By.xpath("//input[@id='inputSurname3']"));
            WebElement frameOneBtn = getDriver().findElement(By.xpath("//button[@type='submit']"));
            firstname.sendKeys("Ajibola");
            surname.sendKeys("oshikoya");
            frameOneBtn.click();
            Thread.sleep(3000);

            getDriver().switchTo().defaultContent();
            getDriver().switchTo().frame(f.findElement(By.xpath("//iframe[@name='iframe2']")));
            getDriver().findElement(By.xpath("//input[@id='inputLogin']")).sendKeys("aoshiko");
            getDriver().findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("aoshiko");
            WebElement continents = getDriver().findElement(By.xpath("//select[@id='inlineFormCustomSelectPref']"));

            Thread.sleep(1000);
            Select select = new Select(continents);
            select.selectByValue("africa");


            List<WebElement> yearOfExperience = getDriver().findElements(By.xpath("//input[@name='gridRadios']"));
                    Random rand = new Random();
                    int index = rand.nextInt(yearOfExperience.size());
                    if(!yearOfExperience.get(index).isSelected()){
                        yearOfExperience.get(index).click();
                        break;
                    }
            Thread.sleep(1000);

            WebElement frameTwoBtn = getDriver().findElement(By.xpath("//button[@type='submit']"));
                    frameTwoBtn.click();



                    getDriver().switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(getDriver(),7);
            WebElement basic = getDriver().findElement(By.xpath("//li[@class='nav-ite dropdown']"));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(basic));
            logger.info("check if basic button is clicked" );
            basic.click();
            logger.debug("check if basic button is clicked {}, " +basic);

                    assert(basic.getAttribute("value").equals("Basic"));




        }
    }
}