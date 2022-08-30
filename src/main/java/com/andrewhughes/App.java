package com.andrewhughes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;

/**
 * Hello world!
 *
 */
public class App 
{

    static int pages = 0;

    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File file = new File("catch-and-shoot-" +
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(timestamp));
        FileOutputStream fileOutput = new FileOutputStream(file, false);
        fileOutput.close();
        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);
        pages = getNumberOfPages(driver);
        int i = 0;
        while(i < pages) {


                // we can click on next page when we are finished
                WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table")));
                String first = firstResult.getText();
                String formattedStr = formatString(first, i);
                appendToFile(formattedStr, i, file);
                WebElement nextButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(
                                By.cssSelector(".stats-table-pagination__next")));
                if(i != pages-1) {
                    new Actions(driver).click(nextButton).perform();
                }

                i++;
        }
        driver.quit();

//        driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER);
//        WebElement firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".table")));
//        // Print the first result
//        String first = firstResult.getText();
//        driver.quit();




        //createFile(firstResult);

//        System.out.println(formatString(first));

    }


    public static int getNumberOfPages(ChromeDriver driver) {
        driver.get("https://www.nba.com/stats/players/catch-shoot/?Season=2021-22&SeasonType=Regular%20Season");
        WebElement tablePaginationInfo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".stats-table-pagination__info")));
        String tablePaginationString = tablePaginationInfo.getText();
        String[] splitted = tablePaginationString.split("\n");
        return Integer.parseInt(splitted[splitted.length-2]);
    }


    public static String formatString(String str, int index) {

        String sb = new String();
        String[] arr = str.split("\n");
        int iterator = 1;
        if(index == 0) {
            iterator = 0;
        }
        while(iterator < arr.length) {
            if(iterator % 2 == 1) {
                sb += arr[iterator] + " ";
            }
            else {
                if(index == pages-1 && iterator == arr.length-1) {
                    // don't append ending \n
                    sb += arr[iterator];
                } else {
                    sb += arr[iterator] + "\n";
                }

            }
            iterator++;
        }
        return sb;
    }

    public static void appendToFile(String results, int index, File file) throws FileNotFoundException {
        // don't append \n to last index

        PrintWriter output = new PrintWriter(new FileOutputStream(file, true));
        output.print(results);
        output.close();
    }

}
