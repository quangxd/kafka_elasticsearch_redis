package superapp.portal.web.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import superapp.portal.web.model.AppStatus;

@RestController
@RequestMapping("/api/v1/writer")
@Slf4j
@RequiredArgsConstructor
public class IWriterController {
    private final KafkaProducer<String, Object> kafkaProducer;
    @PostMapping("/create")
    public ResponseEntity<AppStatus> insert(@RequestBody Object object) {

        log.info("IReaderController.insert START ... with object: {}", object.toString());

        //records with same key are sent to same partition

        ProducerRecord<String, Object> producerRecord =
                new ProducerRecord<>("create-user","key", object);


        kafkaProducer.send(producerRecord, new Callback() {

            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {

                    log.info("Message has been sent!");
                } else {

                    log.error("Exception thrown ... with cause: {}", e.getMessage());
                }
            }
        });

        return null;
    }


    @PutMapping("/update")
    public ResponseEntity<AppStatus> update(@RequestBody Object object) {

        log.info("IReaderController.insert START ... with object: {}", object.toString());

        //records with same key are sent to same partition

        ProducerRecord<String, Object> producerRecord =
                new ProducerRecord<>("update-user", object);


        kafkaProducer.send(producerRecord, new Callback() {

            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {

                    log.info("Message has been sent!");
                } else {

                    log.error("Exception thrown ... with cause: {}", e.getMessage());
                }
            }
        });

        return null;
    }
}
