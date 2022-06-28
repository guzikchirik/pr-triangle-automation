package io.github.guzikchirik.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class LandingPage extends AbstractBasePage {

    public SelenideElement trianglePageLink = $("#triangle");

    public TrianglePage openTrianglePage() {
        this.trianglePageLink.click();
        return new TrianglePage();
    }
}
