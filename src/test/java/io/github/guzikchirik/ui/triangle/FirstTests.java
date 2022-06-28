package io.github.guzikchirik.ui.triangle;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.guzikchirik.page.LandingPage;
import io.github.guzikchirik.page.TrianglePage;
import io.github.guzikchirik.ui.AbstractTest;

public class FirstTests extends AbstractTest {

    private TrianglePage trianglePage;

    @BeforeMethod
    public void detUp() {
        trianglePage = new LandingPage().openTrianglePage();
    }

    @Test
    public void rightAngleTriangleTest() {
        trianglePage.inputSideA("3");
        trianglePage.inputSideB("4");
        trianglePage.inputSideC("5");
        trianglePage.clickShowBtn();
        Assert.assertEquals(trianglePage.getMessage(), "It's an right angled triangle!", "Wrong message!");
    }
}
