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
    public void onPostPersist() {}

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

    public void accept() {
        if(this.getStatus().equals("배달 대기중")){
            this.setStatus("배달중");
            repository().save(this);
            DeliveryStarted deliveryStarted = new DeliveryStarted(this);
            deliveryStarted.publishAfterCommit();
        }else{
            throw new RuntimeException("배달 대기상태가 아닌 주문입니다.");
        }
    }

    public void completedelivery() {
        if(this.getStatus().equals("배달중")){
            this.setStatus("배달 완료");
            repository().save(this);
            Delivered delivered = new Delivered(this);
            delivered.publishAfterCommit();
        }else{
            throw new RuntimeException("배달중상태가 아닌 주문입니다.");
        }
    }

    public static void addDeliveryList(CookFinished cookFinished) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(cookFinished.getOrderId());
        delivery.setAddress(cookFinished.getAddress());
        delivery.setStatus("배달 대기중");
        repository().save(delivery);
    }
}
