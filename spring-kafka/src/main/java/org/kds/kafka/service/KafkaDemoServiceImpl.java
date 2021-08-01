package org.kds.kafka.service;

import org.kds.kafka.config.CommonConfiguration;
import org.kds.kafka.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by kalpasenanayake on 1/10/17.
 */

@Service
@Import({CommonConfiguration.class, ConfigProperties.class})
public class KafkaDemoServiceImpl implements KafkaDemoService {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void sendMessage(String message) {
        template.send(configProperties.getTopic(), message);
    }
}
