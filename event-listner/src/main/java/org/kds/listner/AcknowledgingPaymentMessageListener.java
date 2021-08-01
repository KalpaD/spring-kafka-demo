package org.kds.listner;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.kds.exceptions.InvalidPaymentEventException;
import org.kds.model.PaymentEvent;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AcknowledgingPaymentMessageListener implements AcknowledgingMessageListener<String, PaymentEvent> {

    @Override
    public void onMessage(ConsumerRecord<String, PaymentEvent> consumerRecord, Acknowledgment acknowledgment) {
        log.info("Received message {}, on thread {}", consumerRecord.value(), Thread.currentThread().getName());

        // artificial error
        PaymentEvent paymentEvent = consumerRecord.value();
        if (paymentEvent.getPaymentId().equals("1234")) {
            // default behavior from the consumer is to try 9 times and leave it
            throw new InvalidPaymentEventException("Simulating Error");
        }

    }
}
