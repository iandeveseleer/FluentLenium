package io.fluentlenium.adapter.spock.smoketest

import io.fluentlenium.adapter.spock.FluentSpecification
import io.github.bonigarcia.wdm.managers.ChromeDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

import static org.assertj.core.api.Assertions.assertThat

class SmokeTestSpec extends FluentSpecification {

    def setupSpec() {
        ChromeDriverManager.chromedriver().setup()
    }

    def "smokeTest"() {
        expect:
        assertThat(getDriver()).isInstanceOf(WebDriver.class)
        WebDriver driver = getDriver()
        assertThat(driver).isInstanceOf(ChromeDriver.class)
    }

}

