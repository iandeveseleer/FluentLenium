package io.fluentlenium.test.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import io.fluentlenium.core.domain.FluentWebElement;
import io.fluentlenium.core.events.annotations.BeforeClickOn;
import io.fluentlenium.test.IntegrationFluentTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Not using Events")
class AnnotationsPriorityEventsTest extends IntegrationFluentTest {
    private static List<Integer> beforeClick = new ArrayList<>();

    @Test
    void clickOn() {
        goTo(DEFAULT_URL);
        $("button").click();
        assertThat(beforeClick).containsExactly(5, 2, 1, 0, -2);
    }

    @BeforeClickOn
    private void beforeClickOn(FluentWebElement element) { // NOPMD UnusedPrivateMethod
        beforeClick.add(0);
    }

    @BeforeClickOn(2)
    private void beforeClickOn2(FluentWebElement element) { // NOPMD UnusedPrivateMethod
        beforeClick.add(2);
    }

    @BeforeClickOn(1)
    private void beforeClickOn1(FluentWebElement element) { // NOPMD UnusedPrivateMethod
        beforeClick.add(1);
    }

    @BeforeClickOn(5)
    private void beforeClickOn5(FluentWebElement element) { // NOPMD UnusedPrivateMethod
        beforeClick.add(5);
    }

    @BeforeClickOn(-2)
    private void beforeClickOnNeg2(FluentWebElement element) { // NOPMD UnusedPrivateMethod
        beforeClick.add(-2);
    }

}
