package com.project.challenge.infrastructure.adapters.inbound.handlers;

import com.project.challenge.infrastructure.interfaces.events.dto.SpaceshipActionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.Map;


@Component
public class SpaceshipEventPublisher {

    @Autowired
    private SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String sqsUrl;

    public void publish(SpaceshipActionEvent event) {

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(sqsUrl)
                .messageBody(event.message())
                .messageAttributes(Map.of(
                        "action", MessageAttributeValue.builder().dataType("String").stringValue(event.action()).build()
                ))
                .build();

        sqsClient.sendMessage(sendMessageRequest);
    }


}
