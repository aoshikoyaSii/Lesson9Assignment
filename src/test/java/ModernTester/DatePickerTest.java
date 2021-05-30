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
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatePickerTest extends TestBase {

    @Test
    void shouldPickDate() throws Exception{
       openBrowser();
       openCalender();

       TargetYear("January 2021", 20, 2021);
       TargetYear("February 2021", 2, 2021);
       TargetYear("February 2021", 2, 2021);
       TargetYear("November 2020", 1, 2020);
       TargetYear("December 2020", 1, 2020);
       TargetYear("December 2020", 25, 2020);
       TargetYear("February 2022", 1, 2022);
        TodayUsingLocalDate();
    }

    public void TargetDays(int choiceDay) throws Exception{
        List<WebElement> days = getDriver().findElements(By.xpath("//a[@class='ui-state-default']"));
        for (WebElement day : days) {
            if (Integer.parseInt(day.getText()) == choiceDay) {
                day.click();
                Thread.sleep(3000);
                break;
            }
        }
    }

    void TodayUsingLocalDate() throws Exception {
        LocalDateTime today = LocalDateTime.now();
        String month = today.getMonth().name();
        String formattedDate = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        String str[] = formattedDate.split("/");
        int day = Integer.parseInt(str[0]);
        String year = str[2];
        String val = month+" "+year;
        TargetYear(val,day,2021);
    }

    void TargetYear (String strMonthYear, int day, int year)throws Exception{
        openCalender();

        while(getYearIntValue() > year){
            String monthyear = getDriver().findElement(By.cssSelector("div.ui-datepicker-title")).getText();
            if(strMonthYear.equals(monthyear)){
                break;
            } else {
               getDriver().findElement(By.cssSelector("a.ui-datepicker-prev")).click();
                }
            }

        while(getYearIntValue() < year){
            String monthyear = getDriver().findElement(By.cssSelector("div.ui-datepicker-title")).getText();
            if(strMonthYear.equals(monthyear)){
                break;
            } else {
                getDriver().findElement(By.cssSelector("a.ui-datepicker-next")).click();
            }
        }
            TargetDays(day);
        }

    public int getYearIntValue(){
        List<WebElement> yearVal = getDriver().findElements(By.cssSelector("div.ui-datepicker-title"));
        int yearInt = 0;
        for(WebElement w:  yearVal) {
                  String yr = w.findElement(By.cssSelector("span.ui-datepicker-year")).getText();
                  yearInt = Integer.parseInt(yr);
              }
              return yearInt;
    }

    public void openCalender (){
        getDriver().findElement(By.xpath("//input[@id='datepicker']")).click();
    }

    public void openBrowser(){
        getDriver().get("https://seleniumui.moderntester.pl/datepicker.php");
    }
}


