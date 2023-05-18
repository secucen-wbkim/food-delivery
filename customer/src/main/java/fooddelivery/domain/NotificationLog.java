package fooddelivery.domain;

import fooddelivery.CustomerApplication;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "NotificationLog_table")
@Data
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerId;

    private String message;

    private Long orderId;

    public static NotificationLogRepository repository() {
        NotificationLogRepository notificationLogRepository = CustomerApplication.applicationContext.getBean(
            NotificationLogRepository.class
        );
        return notificationLogRepository;
    }


    // 메신저를 통해 알림을 보내는 부분이 들어가야 하는 곳
    // kakao 메신저 API등을 이용하여 알림 보내는 코드가 짜여져야한다.
    // 로그만 저장

    public static void notifyKakaotalk(Accepted accepted) {
        repository().findByOrderId(accepted.getOrderId()).ifPresent(notificationLog->{
            NotificationLog newNotificationLog = new NotificationLog();
            newNotificationLog.setCustomerId(notificationLog.getCustomerId());
            newNotificationLog.setMessage(notificationLog.getCustomerId() + "님의 주문이 수락되었습니다.");
            repository().save(newNotificationLog);
         });
    }

    public static void notifyKakaotalk(Rejected rejected) {
        repository().findByOrderId(rejected.getOrderId()).ifPresent(notificationLog->{
            NotificationLog newNotificationLog = new NotificationLog();
            newNotificationLog.setCustomerId(notificationLog.getCustomerId());
            newNotificationLog.setMessage(notificationLog.getCustomerId() + "님의 주문이 가게 사정으로 인해 거절되었습니다.");
            repository().save(newNotificationLog);
         });
    }

    public static void notifyKakaotalk(OrderPlaced orderPlaced) {
        NotificationLog notificationLog = new NotificationLog();
        notificationLog.setCustomerId(orderPlaced.getCustomerId());
        notificationLog.setOrderId(orderPlaced.getId());
        notificationLog.setMessage(orderPlaced.getCustomerId() + "님의 주문이 정상적으로 접수 되었습니다.");
        repository().save(notificationLog);
    }

    public static void notifyKakaotalk(DeliveryStarted deliveryStarted) {
        repository().findByOrderId(deliveryStarted.getOrderId()).ifPresent(notificationLog->{
            NotificationLog newNotificationLog = new NotificationLog();
            newNotificationLog.setCustomerId(notificationLog.getCustomerId());
            newNotificationLog.setMessage(notificationLog.getCustomerId() + "님께서 주문하신 요리의 배달이 시작되었습니다.");
            repository().save(newNotificationLog);
         });
    }

    public static void notifyKakaotalk(Delivered delivered) {
        repository().findByOrderId(delivered.getOrderId()).ifPresent(notificationLog->{
            NotificationLog newNotificationLog = new NotificationLog();
            newNotificationLog.setCustomerId(notificationLog.getCustomerId());
            newNotificationLog.setMessage(notificationLog.getCustomerId() + "님께서 주문하신 요리의 배달이 완료되었습니다.");
            repository().save(newNotificationLog);
         });
    }

    public static void notifyKakaotalk(OrderCanceled orderCanceled) {
        repository().findByOrderId(orderCanceled.getId()).ifPresent(notificationLog->{
            NotificationLog newNotificationLog = new NotificationLog();
            newNotificationLog.setCustomerId(notificationLog.getCustomerId());
            newNotificationLog.setMessage(notificationLog.getCustomerId() + "님의 주문이 취소되었습니다.");
            repository().save(newNotificationLog);
         });
    }
}
