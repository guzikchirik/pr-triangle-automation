package io.github.guzikchirik.ui.triangle;

import static com.codeborne.selenide.Selenide.open;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.guzikchirik.page.LandingPage;
import io.github.guzikchirik.page.TrianglePage;
import io.github.guzikchirik.ui.AbstractTest;
import io.github.guzikchirik.utils.PropertiesManager;

public class FirstTests extends AbstractTest {

    private static final String RIGHT_ANGLED_TRIANGLE_MSG = "It's an right angled triangle!";
    private static final String ISOSCELES_TRIANGLE_MSG = "It's an isosceles triangle!";
    private static final String EQUILATERAL_TRIANGLE_MSG = "It's an equilateral triangle!";
    private static final String TRIANGLE_DOES_NOT_EXIST_MSG = "Such a triangle doesn't exist! " +
            "A side of a triangle cannot be greater than the sum of the lengths of the other two sides!";
    private TrianglePage trianglePage;

    @BeforeMethod
    public void setUp() {
        open(PropertiesManager.getProperty("start.url"));
        trianglePage = new LandingPage().openTrianglePage();
    }

    @Test(dataProvider = "triangles")
    public void rightAngleTriangleTest(final String a, final String b, final String c, final String expectedMsg) {
        trianglePage.inputSideA(a);
        trianglePage.inputSideB(b);
        trianglePage.inputSideC(c);
        trianglePage.clickShowBtn();
        Assert.assertEquals(trianglePage.getMessage(), expectedMsg, "Wrong message!");
    }

    @DataProvider
    public static Object[][] triangles() {
        return new Object[][]{
                {"3", "4", "5", RIGHT_ANGLED_TRIANGLE_MSG},
                {"3", "5", "4", RIGHT_ANGLED_TRIANGLE_MSG},
                {"4", "3", "5", RIGHT_ANGLED_TRIANGLE_MSG},
                {"4", "5", "3", RIGHT_ANGLED_TRIANGLE_MSG},
                {"5", "3", "4", RIGHT_ANGLED_TRIANGLE_MSG},
                {"5", "4", "3", RIGHT_ANGLED_TRIANGLE_MSG},

                {"2", "2", "1", ISOSCELES_TRIANGLE_MSG},
                {"2", "1", "2", ISOSCELES_TRIANGLE_MSG},
                {"1", "2", "2", ISOSCELES_TRIANGLE_MSG},

                {"5", "5", "5", EQUILATERAL_TRIANGLE_MSG},

                {"2", "2", "5", TRIANGLE_DOES_NOT_EXIST_MSG},
        };
    }
}
