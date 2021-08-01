package org.kds.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.kds.listner.AcknowledgingPaymentMessageListener;
import org.kds.model.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaListenerConfiguration {

    private final AcknowledgingPaymentMessageListener paymentMessageListener;

    @Autowired
    public KafkaListenerConfiguration(AcknowledgingPaymentMessageListener paymentMessageListener) {
        this.paymentMessageListener = paymentMessageListener;
    }

    @Bean
    public KafkaMessageListenerContainer<String, PaymentEvent> kafkaListenerContainer() {

        var containerProps = new ContainerProperties("payment-topic2");
        containerProps.setMessageListener(paymentMessageListener);
        containerProps.setAckMode(ContainerProperties.AckMode.MANUAL);

        var container = new KafkaMessageListenerContainer<>(paymentConsumerFactory(), containerProps);
        container.setErrorHandler(new SeekToCurrentErrorHandler(new FixedBackOff(1000L, 2L)));
        return container;
    }

    public ConsumerFactory<String, PaymentEvent> paymentConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(PaymentEvent.class));
    }

    // sendind data to given topic as JSON

    //bin/kafka-console-producer.sh --broker-list localhost:9092 --topic payment-topic < payment-event-valid-payid.json

}
