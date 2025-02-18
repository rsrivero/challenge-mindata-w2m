package com.project.challenge.infrastructure.interfaces.events;

import com.project.challenge.infrastructure.adapters.inbound.handlers.MessageHandler;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.annotation.SqsListenerAcknowledgementMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SpaceshipEventConsumer {

    @Autowired
    private MessageHandler messageHandler;

    @SqsListener(queueNames = "${consumer.spaceship.queue}",
            messageVisibilitySeconds = "${consumer.spaceship.message-visibility-seconds}",
            pollTimeoutSeconds = "${consumer.spaceship.polling-interval-seconds}",
            acknowledgementMode = SqsListenerAcknowledgementMode.ON_SUCCESS)
    public void consumer(Message<String> message) {
        var action = message.getHeaders().get("action").toString();
        messageHandler.accept(message.getPayload(), action);
    }
}

