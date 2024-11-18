package io.fluentlenium.utils.chromium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromiumControlImpl implements ChromiumControl {

    private WebDriver driver;

    public ChromiumControlImpl(WebDriver driver) {
        this.driver = driver;
    }

    public final ChromiumApi getChromiumApi() {

        RemoteWebDriver remoteWebDriver;
        try {
            remoteWebDriver = (RemoteWebDriver) driver;
        } catch (ClassCastException ex) {
            throw new ChromiumApiNotSupportedException("API supported only by Chrome and Edge",
                    ex.getCause());
        }
        return new ChromiumApi(remoteWebDriver);
    }

}
