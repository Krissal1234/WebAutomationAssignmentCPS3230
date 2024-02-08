package com.uom.cps3230;

import com.uom.cps3230.enums.ScottsStates;
import nz.ac.waikato.modeljunit.FsmModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScottsTester implements FsmModel {
//System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
//    driver = new ChromeDriver();
//    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebDriver driver;
    WebDriverWait wait;
    ScottsOperator sut = new ScottsOperator(driver, wait);
    ScottsStates state = ScottsStates.HOME;
    @Override
    public Object getState() {
        return state;
    }

    @Override
    public void reset(boolean testing) {
        state = ScottsStates.HOME;

        if(testing){
            System.setProperty("webdriver.chrome.driver", "/home/krissal1234/Documents/projects/uni/software_testing/chromedriver");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            sut = new ScottsOperator(driver,wait);
        }

    }
}
