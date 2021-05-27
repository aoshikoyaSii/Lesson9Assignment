package ModernTester;

import TestBaseFolder.TestBase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SliderTest extends TestBase {

    @Test
    public void shouldMoveSliderTo50() throws Exception{
        getDriver().get("https://seleniumui.moderntester.pl/slider.php");

       Thread.sleep(5000);

        moveSliderTo(50);
        moveSliderTo(30);
        moveSliderTo(30);
        moveSliderTo(80);
    }


    private void moveSliderTo(int expectedSliderValue){
        WebElement slider =  getDriver().findElement(By.id("custom-handle"));

        int clonegetsliderValue = getsliderValue();

        if(expectedSliderValue > getsliderValue()){
            for(int i = 0; i < expectedSliderValue - clonegetsliderValue; i++){
                slider.sendKeys(Keys.ARROW_RIGHT);
            }
        } else {
            for(int i = 0; i < clonegetsliderValue - expectedSliderValue; i++){
                slider.sendKeys(Keys.ARROW_LEFT);
            }
        }
        assertThat(getsliderValue(), equalTo(expectedSliderValue));
    }


    private int getsliderValue(){
        String slidervalue = getDriver().findElement(By.id("custom-handle")).getText();
        return Integer.parseInt(slidervalue);
    }
}
