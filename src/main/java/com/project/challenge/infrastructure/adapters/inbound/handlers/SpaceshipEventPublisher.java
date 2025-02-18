package com.project.challenge.infrastructure.adapters.inbound.handlers;

import com.project.challenge.infrastructure.interfaces.events.dto.SpaceshipActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.Map;


@Component
public class SpaceshipEventPublisher {

    @Autowired
    private SqsClient sqsClient;


    public void publish(SpaceshipActionEvent event) {

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl("http://sqs.us-east-1.localstack:4566/000000000000/sae1-spaceship-dev")
                .messageBody(event.message())
                .messageAttributes(Map.of(
                        "action", MessageAttributeValue.builder().dataType("String").stringValue(event.action()).build()
                ))
                .build();

        sqsClient.sendMessage(sendMessageRequest);
    }


}
