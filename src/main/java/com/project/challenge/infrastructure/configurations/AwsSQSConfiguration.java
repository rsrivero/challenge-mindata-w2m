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

            logger.info("Configurando SQS Client con endpoint: {}", endpointUrl);

            return SqsClient.builder()
                    .credentialsProvider(DefaultCredentialsProvider.create()) // Usa credenciales de AWS
                    .region(Region.US_EAST_1)
                    .endpointOverride(URI.create(endpointUrl)) // Usa valor por defecto si no está en env
                    .build();
        } catch (Exception e) {
            logger.error("Error al configurar el cliente SQS", e);
            throw e;
        }
    }
}
