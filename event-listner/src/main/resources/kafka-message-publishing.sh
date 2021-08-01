#send  data to given topic as JSON
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic payment-topic < payment-event.json