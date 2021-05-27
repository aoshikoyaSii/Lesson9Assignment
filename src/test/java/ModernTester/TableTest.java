package ModernTester;

import TestBaseFolder.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableTest extends TestBase {

    @Test
    void filterTableCellsInformation() throws Exception {
        getDriver().get("https://seleniumui.moderntester.pl/table.php");
        List<WebElement> list = getDriver().findElements(By.cssSelector("tbody"));
        for (WebElement c : list) {
            List<WebElement> getAllCols = c.findElements(By.cssSelector("tr"));
            for(WebElement w: getAllCols){
                List<WebElement> col = w.findElements(By.cssSelector("td"));
                List<WebElement> th = w.findElements(By.cssSelector("th"));
                for(int i = 0; i < th.size(); i ++){
                    if(Integer.parseInt(col.get(3).getText()) > 4000){
                        if(col.get(2).getText().equalsIgnoreCase("switzerland") || (col.get(2).getText().startsWith("S") && col.get(2).getText().endsWith("y"))){
                            System.out.println(th.get(i).getText() +"\t" + col.get(0).getText() +"\t" + col.get(1).getText() +"\t" + col.get(2).getText());
                        }

                    }
                }
            }
        }
    }
}
