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


    private int moveSliderTo(int expectedSliderValue) throws Exception{

        int clonegetsliderValue = getsliderValue();
        if(!(expectedSliderValue == getsliderValue())){
            if(expectedSliderValue > getsliderValue()){
                for(int i = 0; i < expectedSliderValue - clonegetsliderValue; i++){
                    keyDirection("right");
                }
            } else {
                for(int i = 0; i < clonegetsliderValue - expectedSliderValue; i++){
                   keyDirection("left");
                }
            }
        }
        assertThat(getsliderValue(), equalTo(expectedSliderValue));
        return getsliderValue();
    }

    void keyDirection(String slider){
        WebElement sliderEl =  getDriver().findElement(By.id("custom-handle"));
        sliderEl.sendKeys(slider == "left" ? Keys.ARROW_LEFT : Keys.ARROW_RIGHT);

    }

    private int getsliderValue(){
        return Integer.parseInt(getDriver().findElement(By.id("custom-handle")).getText());
    }
}
