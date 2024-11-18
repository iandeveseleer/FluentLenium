package io.fluentlenium.test.events;

import static org.junit.jupiter.api.Assertions.assertThrows;

import io.fluentlenium.configuration.FluentConfiguration;
import io.fluentlenium.core.events.ElementListener;
import io.fluentlenium.test.IntegrationFluentTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@FluentConfiguration(eventsEnabled = FluentConfiguration.BooleanValue.FALSE)
@Disabled("Not using EventFiringWebDriver")
class EventsInvalidDriverTest extends IntegrationFluentTest {

    @Test
    void checkInvalidDriver() {
        assertThrows(IllegalStateException.class,
                () -> {
                    ElementListener beforeListener = Mockito.mock(ElementListener.class);
                    events().beforeClickOn(beforeListener);
                });
    }
}
