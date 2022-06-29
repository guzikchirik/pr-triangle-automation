package io.github.guzikchirik.ui;

import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.codeborne.selenide.Selenide;

import io.github.guzikchirik.driver.SelenideManager;
import io.github.guzikchirik.utils.PropertiesManager;

public abstract class AbstractTest {

    @BeforeSuite
    public void setUpSelenide() {
        new SelenideManager().configure();
    }

    @AfterMethod
    public void tearDown() {
        Selenide.clearBrowserCookies();
    }
}
