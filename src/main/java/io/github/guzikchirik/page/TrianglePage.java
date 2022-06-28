package io.github.guzikchirik.page;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class TrianglePage extends AbstractBasePage {
    private final SelenideElement inputSideA = $("#sideA");
    private final SelenideElement inputSideB = $("#sideB");
    private final SelenideElement inputSideC = $("#sideC");
    private final SelenideElement showBtn = $("#showBtn");
    private final SelenideElement message = $("#msg");

    public boolean isInputSideADisplayed() {
        return this.inputSideA.isDisplayed();
    }

    public boolean isInputSideBDisplayed() {
        return this.inputSideB.isDisplayed();
    }

    public boolean isInputSideCDisplayed() {
        return this.inputSideC.isDisplayed();
    }

    public void inputSideA(final String val) {
        this.inputSideA.setValue(val);
    }

    public void inputSideB(final String val) {
        this.inputSideB.setValue(val);
    }

    public void inputSideC(final String val) {
        this.inputSideC.setValue(val);
    }

    public void clickShowBtn() {
        this.showBtn.click();
    }

    public String getMessage() {
        return this.message.getText();
    }
}
