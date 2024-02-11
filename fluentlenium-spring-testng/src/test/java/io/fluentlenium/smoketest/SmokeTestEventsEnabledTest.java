package io.fluentlenium.smoketest;

import io.fluentlenium.IntegrationFluentTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class SmokeTestEventsEnabledTest extends IntegrationFluentTestNg {

    @SuppressWarnings("ConstantConditions")
    @Test
    public void smokeTest() {
        assertThat(getDriver()).isInstanceOf(EventFiringDecorator.class);
        WebDriver driver = new EventFiringDecorator<>().decorate(getDriver());
        assertThat(driver).isInstanceOf(ChromeDriver.class);
    }

}
