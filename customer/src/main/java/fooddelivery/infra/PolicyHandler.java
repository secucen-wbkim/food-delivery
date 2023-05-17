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
    NotificationLogRepository notificationLogRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Accepted'"
    )
    public void wheneverAccepted_NotifyKakaotalk(@Payload Accepted accepted) {
        Accepted event = accepted;
        System.out.println(
            "\n\n##### listener NotifyKakaotalk : " + accepted + "\n\n"
        );

        // Sample Logic //
        NotificationLog.notifyKakaotalk(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Rejected'"
    )
    public void wheneverRejected_NotifyKakaotalk(@Payload Rejected rejected) {
        Rejected event = rejected;
        System.out.println(
            "\n\n##### listener NotifyKakaotalk : " + rejected + "\n\n"
        );

        // Sample Logic //
        NotificationLog.notifyKakaotalk(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_NotifyKakaotalk(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener NotifyKakaotalk : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        NotificationLog.notifyKakaotalk(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryStarted'"
    )
    public void wheneverDeliveryStarted_NotifyKakaotalk(
        @Payload DeliveryStarted deliveryStarted
    ) {
        DeliveryStarted event = deliveryStarted;
        System.out.println(
            "\n\n##### listener NotifyKakaotalk : " + deliveryStarted + "\n\n"
        );

        // Sample Logic //
        NotificationLog.notifyKakaotalk(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Delivered'"
    )
    public void wheneverDelivered_NotifyKakaotalk(
        @Payload Delivered delivered
    ) {
        Delivered event = delivered;
        System.out.println(
            "\n\n##### listener NotifyKakaotalk : " + delivered + "\n\n"
        );

        // Sample Logic //
        NotificationLog.notifyKakaotalk(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCanceled'"
    )
    public void wheneverOrderCanceled_NotifyKakaotalk(
        @Payload OrderCanceled orderCanceled
    ) {
        OrderCanceled event = orderCanceled;
        System.out.println(
            "\n\n##### listener NotifyKakaotalk : " + orderCanceled + "\n\n"
        );

        // Sample Logic //
        NotificationLog.notifyKakaotalk(event);
    }
}
