package com.david.demo.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(final String message) {
        System.out.println("Publishing custom event. ");
        ApplicationEvent customSpringEvent = new ApplicationEvent(message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
