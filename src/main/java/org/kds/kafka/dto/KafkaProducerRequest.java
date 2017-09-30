package org.kds.kafka.dto;

/**
 * Created by kalpasenanayake on 1/10/17.
 */
public class KafkaProducerRequest {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
