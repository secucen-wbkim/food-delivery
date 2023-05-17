package fooddelivery.infra;

import fooddelivery.config.kafka.KafkaProcessor;
import fooddelivery.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusViewHandler {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            OrderStatus orderStatus = new OrderStatus();
            // view 객체에 이벤트의 Value 를 set 함
            orderStatus.setId(orderPlaced.getId());
            orderStatus.setFoodId(orderPlaced.getFoodId());
            orderStatus.setStatus("주문됨");
            // view 레파지 토리에 save
            orderStatusRepository.save(orderStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_1(@Payload Paid paid) {
        try {
            if (!paid.validate()) return;
            // view 객체 조회
            Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(
                paid.getOrderId()
            );

            if (orderStatusOptional.isPresent()) {
                OrderStatus orderStatus = orderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus("결재됨");
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAccepted_then_UPDATE_2(@Payload Accepted accepted) {
        try {
            if (!accepted.validate()) return;
            // view 객체 조회
            Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(
                accepted.getOrderId()
            );

            if (orderStatusOptional.isPresent()) {
                OrderStatus orderStatus = orderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus("접수됨");
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_UPDATE_3(
        @Payload DeliveryStarted deliveryStarted
    ) {
        try {
            if (!deliveryStarted.validate()) return;
            // view 객체 조회
            Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(
                deliveryStarted.getOrderId()
            );

            if (orderStatusOptional.isPresent()) {
                OrderStatus orderStatus = orderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus("배달시작됨");
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRejected_then_UPDATE_4(@Payload Rejected rejected) {
        try {
            if (!rejected.validate()) return;
            // view 객체 조회
            Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(
                rejected.getOrderId()
            );

            if (orderStatusOptional.isPresent()) {
                OrderStatus orderStatus = orderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus("거절됨");
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_UPDATE_5(
        @Payload OrderCanceled orderCanceled
    ) {
        try {
            if (!orderCanceled.validate()) return;
            // view 객체 조회
            Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(
                orderCanceled.getId()
            );

            if (orderStatusOptional.isPresent()) {
                OrderStatus orderStatus = orderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus("주문취소됨");
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDelivered_then_UPDATE_6(@Payload Delivered delivered) {
        try {
            if (!delivered.validate()) return;
            // view 객체 조회
            Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(
                delivered.getOrderId()
            );

            if (orderStatusOptional.isPresent()) {
                OrderStatus orderStatus = orderStatusOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                orderStatus.setStatus("배달완료됨");
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
