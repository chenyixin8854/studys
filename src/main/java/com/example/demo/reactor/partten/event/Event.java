package com.example.demo.reactor.partten.event;

import com.example.demo.reactor.partten.InputSource;

/**
 * @Author: feiweiwei
 * @Description: reactor模式中内部处理的event类
 * @Created Date: 11:03 17/10/12.
 * @Modify by:
 */
public class Event {
    private InputSource source;
    private EventType type;

    public InputSource getSource() {
        return source;
    }

    public void setSource(InputSource source) {
        this.source = source;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
