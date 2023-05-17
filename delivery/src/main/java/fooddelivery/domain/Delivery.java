package fooddelivery.domain;

import fooddelivery.DeliveryApplication;
import fooddelivery.domain.Delivered;
import fooddelivery.domain.DeliveryStarted;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    private Long orderId;

    private String status;

    @PostPersist
    public void onPostPersist() {
        Delivered delivered = new Delivered(this);
        delivered.publishAfterCommit();

        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    @PreUpdate
    public void onPreUpdate() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void addDeliveryList(CookFinished cookFinished) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process
        
        repository().findById(cookFinished.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

    }
}
