package io.github.guzikchirik.driver;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;
import static com.codeborne.selenide.Configuration.headless;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.codeborne.selenide.Configuration;

import io.github.guzikchirik.utils.PropertiesManager;

public class SelenideManager {

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private String browserType = PropertiesManager.getProperty("browser.name");

    public void configure() {
        configure(browserType, headless);
    }

    public void configure(String browserName, boolean headless) {
        if (Boolean.parseBoolean(PropertiesManager.getProperty("isRemote"))) {
            Configuration.remote = PropertiesManager.getProperty("remoteUrl");
        }
        if (headless) {
            capabilities.setCapability("enableVNC", false);
        } else {
            capabilities.setCapability("enableVNC", true);
        }

        Configuration.browser = browserType;
        Configuration.browserSize = "1920x1080";
        capabilities.setJavascriptEnabled(true);
        capabilities.setBrowserName(browserName);
        String tz = System.getProperty("user.timezone");
        capabilities.setCapability("timeZone", tz);

        switch (browserType) {
            case FIREFOX:
                setFireFox();
                break;
            case CHROME:
                setChrome(headless);
                break;
            default:
                throw new IllegalArgumentException("Invalid browserType specified");
        }
        Configuration.browserCapabilities = capabilities;
    }

    private void setFireFox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("media.navigator.streams.fake", true);
        options.addPreference("media.navigator.permission.disabled", true);
        options.setAcceptInsecureCerts(true);
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk",
                              "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        options.addPreference("pdfjs.disabled", true);
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
    }

    private void setChrome(boolean headless) {
        capabilities.setCapability("seleniumProtocol", "WebDriver");
//        capabilities.setVersion("93.0");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream",
                             "--enable-usermedia-screen-capturing", "--allow-real-media", "--test-type", "--disable-popup-blocking");
        if (headless) {
            options.addArguments("--headless");
        }
//        options.addArguments("--no-sandbox");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("prompt_for_download", false);
        prefs.put("directory_upgrade", true);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }
}
