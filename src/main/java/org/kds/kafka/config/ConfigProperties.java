package org.kds.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by kalpasenanayake on 29/9/17.
 */

@ConfigurationProperties(prefix = "kafka")
public class ConfigProperties {

    private String brokerAddress;

    private String topic;

    private String fooTopic;

    public String getBrokerAddress() {
        return brokerAddress;
    }

    public void setBrokerAddress(String brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFooTopic() {
        return fooTopic;
    }

    public void setFooTopic(String fooTopic) {
        this.fooTopic = fooTopic;
    }
}
