package io.fluentlenium.core.events;

import io.fluentlenium.core.components.ComponentInstantiator;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.Objects;

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
