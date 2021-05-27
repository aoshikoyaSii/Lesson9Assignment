package ModernTester;

import TestBaseFolder.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProgressBar extends TestBase {
    @Test
    void TestProgressBar(){
        getDriver().get("https://seleniumui.moderntester.pl/progressbar.php");
        WebElement complete = getDriver().findElement(By.className("progress-label"));
        WebDriverWait waits = new WebDriverWait(getDriver(), 10);
        waits.until(ExpectedConditions.textToBePresentInElement(complete,"Complete!"));
        assertThat(complete.getText(), equalTo("Complete!"));
    }
}
