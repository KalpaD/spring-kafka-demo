package org.kds.listner;

import lombok.extern.slf4j.Slf4j;
import org.kds.exceptions.InvalidPaymentEventException;
import org.kds.model.PaymentEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentMessageListener {


    @KafkaListener(topics = "payment-topic2", groupId = "group", containerFactory = "paymentKafkaListenerContainerFactory")
    public void consume(PaymentEvent event) {
        log.info("Received message {}, on thread {}", event, Thread.currentThread().getName());

        if (event.getPaymentId().equals("1234")) {
            throw new InvalidPaymentEventException("invalid payment event");
        }
    }
}
