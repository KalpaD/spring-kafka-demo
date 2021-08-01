package org.kds.kafka.api;

import org.kds.kafka.dto.KafkaProducerRequest;
import org.kds.kafka.service.KafkaDemoService;
import org.kds.kafka.service.KafkaDemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kalpasenanayake on 1/10/17.
 */
@Controller
public class KafkaDemoController {

    @Autowired
    KafkaDemoService kafkaDemoService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody KafkaProducerRequest request) {
        kafkaDemoService.sendMessage(request.getMessage());
        ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }
}
