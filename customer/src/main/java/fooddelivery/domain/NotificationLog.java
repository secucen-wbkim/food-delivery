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

    public static NotificationLogRepository repository() {
        NotificationLogRepository notificationLogRepository = CustomerApplication.applicationContext.getBean(
            NotificationLogRepository.class
        );
        return notificationLogRepository;
    }

    public static void notifyKakaotalk(Accepted accepted) {
        /** Example 1:  new item 
        NotificationLog notificationLog = new NotificationLog();
        repository().save(notificationLog);

        */

        /** Example 2:  finding and process
        
        repository().findById(accepted.get???()).ifPresent(notificationLog->{
            
            notificationLog // do something
            repository().save(notificationLog);


         });
        */

    }

    public static void notifyKakaotalk(Rejected rejected) {
        /** Example 1:  new item 
        NotificationLog notificationLog = new NotificationLog();
        repository().save(notificationLog);

        */

        /** Example 2:  finding and process
        
        repository().findById(rejected.get???()).ifPresent(notificationLog->{
            
            notificationLog // do something
            repository().save(notificationLog);


         });
        */

    }

    public static void notifyKakaotalk(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        NotificationLog notificationLog = new NotificationLog();
        repository().save(notificationLog);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(notificationLog->{
            
            notificationLog // do something
            repository().save(notificationLog);


         });
        */

    }

    public static void notifyKakaotalk(DeliveryStarted deliveryStarted) {
        /** Example 1:  new item 
        NotificationLog notificationLog = new NotificationLog();
        repository().save(notificationLog);

        */

        /** Example 2:  finding and process
        
        repository().findById(deliveryStarted.get???()).ifPresent(notificationLog->{
            
            notificationLog // do something
            repository().save(notificationLog);


         });
        */

    }

    public static void notifyKakaotalk(Delivered delivered) {
        /** Example 1:  new item 
        NotificationLog notificationLog = new NotificationLog();
        repository().save(notificationLog);

        */

        /** Example 2:  finding and process
        
        repository().findById(delivered.get???()).ifPresent(notificationLog->{
            
            notificationLog // do something
            repository().save(notificationLog);


         });
        */

    }

    public static void notifyKakaotalk(OrderCanceled orderCanceled) {
        /** Example 1:  new item 
        NotificationLog notificationLog = new NotificationLog();
        repository().save(notificationLog);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(notificationLog->{
            
            notificationLog // do something
            repository().save(notificationLog);


         });
        */

    }
}
