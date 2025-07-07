package com.project.challenge.infrastructure.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;
@Configuration
public class AwsSQSConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(AwsSQSConfiguration.class);

    @Bean
    public SqsClient sqsClient() {
        try {
            String endpointUrl = System.getenv("AWS_ENDPOINT_URL");

            if (endpointUrl == null || endpointUrl.isEmpty()) {
                logger.warn("AWS_ENDPOINT_URL no está definido, usando un valor por defecto.");
                endpointUrl = "http://localhost:4566";
            }
            logger.info("Configurando SQS Client con endpoint: {}", endpointUrl);

            return SqsClient.builder()
                    .credentialsProvider(DefaultCredentialsProvider.create())
                    .region(Region.US_EAST_1)
                    .endpointOverride(URI.create(endpointUrl))
                    .build();
        } catch (Exception e) {
            logger.error("Error al configurar el cliente SQS", e);
            throw e;
        }
    }
}
