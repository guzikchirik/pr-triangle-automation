package io.github.guzikchirik.ui;

import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.guzikchirik.driver.SelenideManager;
import io.github.guzikchirik.utils.PropertiesManager;

public abstract class AbstractTest {

    @BeforeClass
    public void setUp() {
        new SelenideManager().configure();
        open(PropertiesManager.getProperty("start.url"));
    }

    @AfterClass
    public void tearDown() {

    }
}
