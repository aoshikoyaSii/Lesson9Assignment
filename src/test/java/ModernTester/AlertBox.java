package ModernTester;

import TestBaseFolder.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AlertBox extends TestBase {

    @Test
    public void promptBox() throws Exception {
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        String expectedResult = "Hello Lord Vader! How are you today?";
        getDriver().findElement(By.id("prompt-alert")).click();
        getDriver().switchTo().alert().sendKeys("Lord Vader");
        getDriver().switchTo().alert().accept();
        assertThat(getDriver().findElement(By.id("prompt-label")).getText(),
                equalTo(expectedResult));
    }


    @Test
    public void ConfirmAlertbox() throws Exception {
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        getDriver().findElement(By.id("confirm-alert")).click();
        getDriver().switchTo().alert().accept();
        String txtAccept = getDriver().findElement(By.id("confirm-label")).getText();
        if (!txtAccept.equals("")) {
            assertThat(txtAccept,
                    equalTo("You pressed OK!"));
        }
        if (txtAccept.equals("You pressed OK!")) {
            getDriver().findElement(By.id("confirm-alert")).click();
            getDriver().switchTo().alert().dismiss();
            assertThat(getDriver().findElement(By.id("confirm-label")).getText(),
                    equalTo("You pressed Cancel!"));
        }
    }


    @Test
    void TestDelayedAlert() throws Exception{
        getDriver().get("https://seleniumui.moderntester.pl/alerts.php");
        WebElement btnDelay = getDriver().findElement(By.id("delayed-alert"));
        WebElement txtOutput = getDriver().findElement(By.id("delayed-alert-label"));

        WebDriverWait waits = new WebDriverWait(getDriver(), 7);
        btnDelay.click();
        waits.until(ExpectedConditions.alertIsPresent());
        getDriver().switchTo().alert().accept();
        assertThat(txtOutput.getText(), equalTo("OK button pressed"));
    }


}