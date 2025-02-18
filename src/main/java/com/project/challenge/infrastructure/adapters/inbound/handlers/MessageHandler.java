package com.project.challenge.infrastructure.adapters.inbound.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    public void accept(String message, String action) {
            logger.info(message);
            logger.info(action);
    }

}
