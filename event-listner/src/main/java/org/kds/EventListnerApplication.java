package org.kds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class EventListnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventListnerApplication.class, args);
	}

}
