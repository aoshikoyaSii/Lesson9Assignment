package ModernTester;

import TestBaseFolder.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;


public class DroppableTest extends TestBase {

    @Test
    public void shouldDragAndDrop() throws Exception{
        getDriver().get("https://seleniumui.moderntester.pl/droppable.php");

        WebElement drag = getDriver().findElement(By.id("draggable"));
        WebElement drop = getDriver().findElement(By.id("droppable"));
        WebElement text = getDriver().findElement(By.xpath("//*[@id='droppable']/p"));

        Actions actions = new Actions(getDriver());
        //actions.dragAndDrop(drag,drop).release().perform();

        int y = drop.getLocation().getY();
        int x = drag.getLocation().getX();
        actions.dragAndDrop(drag,drop).moveByOffset(205,50).moveToElement(drag, x/100,y/1000).release().perform();
        assert(drop.getText().equals("Dropped!"));
    }
}
