package fooddelivery.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.config.kafka.KafkaProcessor;
import fooddelivery.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    StoreOrderRepository storeOrderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Paid'"
    )
    public void wheneverPaid_ChangeOrderStatus(@Payload Paid paid) {
        Paid event = paid;
        System.out.println(
            "\n\n##### listener ChangeOrderStatus : " + paid + "\n\n"
        );

        // Sample Logic //
        StoreOrder.changeOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCanceled'"
    )
    public void wheneverOrderCanceled_NotifyCancel(
        @Payload OrderCanceled orderCanceled
    ) {
        OrderCanceled event = orderCanceled;
        System.out.println(
            "\n\n##### listener NotifyCancel : " + orderCanceled + "\n\n"
        );

        // Sample Logic //
        StoreOrder.notifyCancel(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_CopyOrder(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener CopyOrder : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        StoreOrder.copyOrder(event);
    }
}
