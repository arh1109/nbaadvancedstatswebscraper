package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.nba.com/stats/players/catch-shoot/?Season=2021-22&SeasonType=Regular%20Season");
        String title = driver.getTitle();
//        driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table")));
        // Print the first result
        System.out.println(firstResult.getText());
        driver.quit();


    }
}
