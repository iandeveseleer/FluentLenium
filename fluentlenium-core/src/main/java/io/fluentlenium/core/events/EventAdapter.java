package io.fluentlenium.core.events;

import io.fluentlenium.core.components.ComponentInstantiator;
import io.fluentlenium.core.domain.FluentWebElement;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

/**
 * FluentLenium adapter for Selenium events listener.
 */
class EventAdapter implements WebDriverListener {

    private final EventListener listener;
    private final ComponentInstantiator instantiator;

    /**
     * Creates a new event adapter.
     *
     * @param listener     underlysing listener
     * @param instantiator component instantiator
     */
    EventAdapter(EventListener listener, ComponentInstantiator instantiator) {
        this.listener = listener;
        this.instantiator = instantiator;
    }

    public void beforeNavigateTo(String url, WebDriver driver) {
        listener.beforeNavigateTo(url, driver);
    }

    public void afterNavigateTo(String url, WebDriver driver) {
        listener.afterNavigateTo(url, driver);
    }

    public void beforeNavigateBack(WebDriver driver) {
        listener.beforeNavigateBack(driver);
    }

    public void afterNavigateBack(WebDriver driver) {
        listener.afterNavigateBack(driver);
    }

    public void beforeNavigateForward(WebDriver driver) {
        listener.beforeNavigateForward(driver);
    }

    public void afterNavigateForward(WebDriver driver) {
        listener.afterNavigateForward(driver);
    }

    public void beforeNavigateRefresh(WebDriver driver) {
        listener.beforeNavigateRefresh(driver);
    }

    public void afterNavigateRefresh(WebDriver driver) {
        listener.afterNavigateRefresh(driver);
    }

    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        listener
                .beforeFindBy(by, element == null ? null : instantiator.newComponent(FluentWebElement.class, element), driver);
    }

    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        listener
                .afterFindBy(by, element == null ? null : instantiator.newComponent(FluentWebElement.class, element), driver);
    }

    public void beforeClickOn(WebElement element, WebDriver driver) {
        listener.beforeClickOn(element == null ? null : instantiator.newComponent(FluentWebElement.class, element), driver);
    }

    public void afterClickOn(WebElement element, WebDriver driver) {
        listener.afterClickOn(element == null ? null : instantiator.newComponent(FluentWebElement.class, element), driver);
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] charSequence) {
        listener
                .beforeChangeValueOf(element == null ? null : instantiator.newComponent(FluentWebElement.class, element), driver,
                        charSequence);
    }

    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] charSequence) {
        listener
                .afterChangeValueOf(element == null ? null : instantiator.newComponent(FluentWebElement.class, element), driver,
                        charSequence);
    }

    public void beforeScript(String script, WebDriver driver) {
        listener.beforeScript(script, driver);
    }

    public void afterScript(String script, WebDriver driver) {
        listener.afterScript(script, driver);
    }

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {
        listener.beforeSwitchToWindow(s, webDriver);
    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {
        listener.afterSwitchToWindow(s, webDriver);
    }

    public void onException(Throwable throwable, WebDriver driver) {
        listener.onException(throwable, driver);
    }

    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {
        listener.beforeGetScreenshotAs(outputType);
    }

    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {
        listener.afterGetScreenshotAs(outputType, x);
    }

    public void beforeAlertAccept(WebDriver driver) {
        listener.beforeAlertAccept(driver);
    }

    public void afterAlertAccept(WebDriver driver) {
        listener.afterAlertAccept(driver);
    }

    public void beforeAlertDismiss(WebDriver driver) {
        listener.beforeAlertDismiss(driver);
    }

    public void afterAlertDismiss(WebDriver driver) {
        listener.afterAlertDismiss(driver);
    }

    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        listener.beforeGetText(instantiator.newComponent(FluentWebElement.class, webElement), webDriver);
    }

    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        listener.afterGetText(instantiator.newComponent(FluentWebElement.class, webElement), webDriver, s);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventAdapter that = (EventAdapter) obj;
        return Objects.equals(listener, that.listener);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listener);
    }
}
