package me.zihasz.zware.api.event.events;

import me.yagel15637.blitz.event.Event;
import me.yagel15637.blitz.modifiers.EventEra;

public class ExampleEvent extends Event {
    public ExampleEvent(EventEra era) {
        super(era);
    }

    // USAGE: ZWare.EVENT_BUS.dispatch(new ExampleEvent(EventEra.ERA));
    // NOTE: You can also have variables in events.
}
