package io.fluentlenium.test.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import io.fluentlenium.core.FluentControl;
import io.fluentlenium.core.components.ComponentInstantiator;
import io.fluentlenium.core.domain.FluentList;
import io.fluentlenium.core.domain.FluentWebElement;
import io.fluentlenium.core.events.annotations.AfterClickOn;
import io.fluentlenium.core.events.annotations.AfterFindBy;
import io.fluentlenium.core.events.annotations.BeforeClickOn;
import io.fluentlenium.core.events.annotations.BeforeFindBy;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

@Disabled("Not using Events")
public class AnnotationsComponentEventsTest extends AnnotationsComponentEventsTestSubClass {
    private final List<WebElement> beforeClick = new ArrayList<>();

    public static class Component extends FluentWebElement {

        private int beforeClick;
        private int afterClick;

        private final List<By> beforeFindBy = new ArrayList<>();
        private final List<By> afterFindBy = new ArrayList<>();

        public Component(WebElement webElement, FluentControl fluentControl, ComponentInstantiator instantiator) {
            super(webElement, fluentControl, instantiator);
        }

        @BeforeClickOn
        public void beforeClickOn() {
            assertThat(afterClick).isEqualTo(beforeClick);
            beforeClick++;
        }

        @AfterClickOn
        public void afterClickOn() {
            assertThat(beforeClick).isEqualTo(afterClick + 1);
            afterClick++;
        }

        @BeforeFindBy
        public void beforeFindBy(By by) {
            beforeFindBy.add(by);
        }

        @AfterFindBy
        public void afterFindBy(By by) {
            assertThat(beforeFindBy).hasSize(afterFindBy.size() + 1);
            afterFindBy.add(by);
        }
    }

    @Test
    void clickOnFirst() {
        goTo(DEFAULT_URL);

        Component button = el("button").as(Component.class);
        button.click();

        Component otherButton = el("button").as(Component.class);

        assertThat(button.beforeClick).isEqualTo(1);
        assertThat(button.afterClick).isEqualTo(1);

        assertThat(otherButton.beforeClick).isEqualTo(0);
        assertThat(otherButton.afterClick).isEqualTo(0);

        assertThat(beforeClick).containsExactly(unwrapElement(button.getElement()));
        assertThat(afterClick).containsExactly(unwrapElement(button.getElement()));

    }

    private WebElement unwrapElement(WebElement element) {
        if (element instanceof WrapsElement) {
            WebElement wrappedElement = ((WrapsElement) element).getWrappedElement();
            if (wrappedElement != element && wrappedElement != null) { // NOPMD CompareObjectsWithEquals
                return unwrapElement(wrappedElement);
            }
        }
        return element;
    }

    @Test
    void clickOn() {
        goTo(DEFAULT_URL);

        FluentList<Component> buttons = $("button").as(Component.class);
        buttons.click();

        FluentList<Component> otherButtons = $("button").as(Component.class);

        for (Component button : buttons) {
            assertThat(button.beforeClick).isEqualTo(1);
            assertThat(button.afterClick).isEqualTo(1);
        }

        for (Component button : otherButtons) {
            assertThat(button.beforeClick).isEqualTo(0);
            assertThat(button.afterClick).isEqualTo(0);
        }

        List<WebElement> elements = new ArrayList<>();
        for (Component button : buttons) {
            elements.add(unwrapElement(button.getElement()));
        }

        assertThat(beforeClick).containsExactlyElementsOf(elements);
        assertThat(afterClick).containsExactlyElementsOf(elements);

    }

    @BeforeClickOn
    private void beforeClickOn(FluentWebElement element) { // NOPMD UnusedPrivateMethod
        beforeClick.add(element.getElement());
    }

    @Test
    void findBy() {
        goTo(DEFAULT_URL);

        Component htmlComponent = el("html").as(Component.class);
        htmlComponent.el("button").present();

        Component otherHtmlComponent = el("html").as(Component.class);
        otherHtmlComponent.present();

        assertThat(htmlComponent.beforeFindBy).hasSize(1);
        assertThat(htmlComponent.afterFindBy).hasSize(1);

        assertThat(htmlComponent.beforeFindBy).containsExactly(By.cssSelector("button"));
        assertThat(htmlComponent.afterFindBy).containsExactly(By.cssSelector("button"));

        assertThat(otherHtmlComponent.beforeFindBy).isEmpty();
        assertThat(otherHtmlComponent.afterFindBy).isEmpty();
    }
}
