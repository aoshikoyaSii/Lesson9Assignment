package ModernTester;

import TestBaseFolder.TestBase;
import com.google.common.base.Function;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class DatePickerTest extends TestBase {
    String[] monthArray = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    @Test
    void shouldPickDate() throws Exception{

        getDriver().get("https://seleniumui.moderntester.pl/datepicker.php");
//        selectDate(29,5,2021);
        TargetYear(2027, 6);
        Thread.sleep(5000);
    }


    void selectDate(int day, int month, int year) throws Exception{
        getDriver().findElement(By.xpath("//input[@id='datepicker']")).click();
        TargetMonth(month);
        TargetDays(day);
        TargetYear(year,6);
        Thread.sleep(8000);
    }


    //function target day
    int TargetDays(int choiceDay) {
        List<WebElement> days = getDriver().findElements(By.xpath("//a[@class='ui-state-default']"));
        for (WebElement day : days) {
            if (Integer.parseInt(day.getText()) == choiceDay) {
                day.click();
                break;
            }
        }
        return choiceDay;
    }


    //function target month
    int TargetMonth(int month) {
        Actions actions = new Actions(getDriver());
        getDriver().findElement(By.xpath("//input[@id='datepicker']")).click();
        String monthEl = getDriver().findElement(By.className("ui-datepicker-month")).getText();
        int index = -1;
        for (int i = 0; i < monthArray.length; i++) {
            if (monthArray[i].equals(monthEl)) {
                index = i;
                break;
            }
        }
            while (index != month) {
                if (index > month) {
                    actions.click(getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-prev ui-corner-all')]"))).click().perform();
                    break;
                } else if (index < month) {
                    actions.click(getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]"))).click().perform();
                    break;
                } else
                    return index;
            }

        return index;
    }

        //function target year
        int TargetYear ( int year, int month) throws Exception{
            getDriver().findElement(By.xpath("//input[@id='datepicker']")).click();

            WebDriverWait wait = new WebDriverWait(getDriver(), 10);
            WebElement yearValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-datepicker-year")));
            Actions actions = new Actions(getDriver());
            int convertToInt = Integer.parseInt(yearValue.getText());

            while (true) {
                int actualYear = convertToInt;
                if (actualYear < year) {
                    actions.click(getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]"))).click().perform();
                    TargetMonth(month);
                    break;
                }
                if (actualYear > year) {
                    getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-prev ui-corner-all')]")).click();
                    TargetMonth(month);
                } else {
                    return actualYear;
                }
                    break;
            }
           return year;
        }

        // function to change date and month
//        void changeMonthAndYear(int month, int year) throws Exception{
//        Actions actions = new Actions(getDriver());
//            getDriver().findElement(By.xpath("//input[@id='datepicker']")).click();
//
//
//            WebElement btnPrev =   getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-prev ui-corner-all')]"));
//            WebElement btnNext = getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]"));
//
//            String monthEl = getDriver().findElement(By.className("ui-datepicker-month")).getText();
//            String[] monthArray = {"January","February","March","April","May","June","July","August","September","October","November","December"};
//
//
//                int index = -1;
//                for (int i = 0; i < monthArray.length; i++) {
//                    if (monthArray[i].equals(monthEl)) {
//                            index = i;
//                            break;
//                    }}
//            while(true) {
//                    if((index < month)){
//                        actions.click(btnNext).click().perform();
//                        break;
//                    } else if((index > month )){
//                        actions.click(btnPrev).click().perform();
//                        break;
//                    }
//                findYear(year);
//                break;
//            }
//            getDriver().findElement(By.xpath("//input[@id='datepicker']")).click();
////            while(true) {
////                int currentYear = findYear();
////                if (currentYear < year) {
////                    Actions action = new Actions(getDriver());
////                    action.build();
////                    action.click(btnNext).click();
////                    break;
////                    //getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-next ui-corner-all')]")).click();
////                } else if (currentYear > year) {
////                    Actions action = new Actions(getDriver());
////                    action.build();
////                    action.click(btnPrev).click();
////                    break;
////                    //getDriver().findElement(By.xpath("//a[contains(@class,'ui-datepicker-prev ui-corner-all')]")).click();
////                }
////                break;
////            }
//            Thread.sleep(5000);
//        }

    private int findYear() throws Exception {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        WebElement yearElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-datepicker-year")));
        String year = yearElement.getText();
        return Integer.parseInt(year);
    }

}


