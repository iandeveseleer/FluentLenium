package io.fluentlenium.smoketest;

import static org.assertj.core.api.Assertions.assertThat;

import io.fluentlenium.IntegrationFluentTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class SmokeTestEventsEnabledTest extends IntegrationFluentTestNg {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void smokeTest() {
        assertThat(getDriver()).isInstanceOf(WebDriver.class);
        WebDriver driver = (WebDriver) getDriver();
        assertThat(driver).isInstanceOf(ChromeDriver.class);
    }
}
