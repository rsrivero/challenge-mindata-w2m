#!/bin/bash
export AWS_REGION=us-east-1

QUEUE_NAME="sae1-spaceship-dev"
ENDPOINT_URL="http://localstack:4566"

if awslocal --endpoint-url="$ENDPOINT_URL" sqs list-queues | grep -q "$QUEUE_NAME"; then
    echo "La cola $QUEUE_NAME ya existe."
else
    awslocal --endpoint-url="$ENDPOINT_URL" sqs create-queue --queue-name "$QUEUE_NAME"
    echo "Cola $QUEUE_NAME creada con éxito."
fi
