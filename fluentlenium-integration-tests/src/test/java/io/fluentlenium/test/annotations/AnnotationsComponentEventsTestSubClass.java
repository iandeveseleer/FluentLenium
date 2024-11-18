package io.fluentlenium.test.annotations;

import io.fluentlenium.core.domain.FluentWebElement;
import io.fluentlenium.core.events.annotations.BeforeClickOn;
import io.fluentlenium.test.IntegrationFluentTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.WebElement;

@Disabled("Not using Events")
class AnnotationsComponentEventsTestSubClass extends IntegrationFluentTest {

    List<WebElement> afterClick = new ArrayList<>();

    @BeforeClickOn
    private void afterClickOn(FluentWebElement element) { // NOPMD UnusedPrivateMethod
        afterClick.add(element.getElement());
    }
}
