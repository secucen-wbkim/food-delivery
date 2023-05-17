package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.Accepted;
import fooddelivery.domain.CookFinished;
import fooddelivery.domain.CookStarted;
import fooddelivery.domain.Rejected;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "StoreOrder_table")
@Data
public class StoreOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foodId;

    private Long orderId;

    private String status;

    private String address;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {
        Accepted accepted = new Accepted(this);
        accepted.publishAfterCommit();

        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    @PreUpdate
    public void onPreUpdate() {
        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        Rejected rejected = new Rejected(this);
        rejected.publishAfterCommit();
    }

    public static StoreOrderRepository repository() {
        StoreOrderRepository storeOrderRepository = StoreApplication.applicationContext.getBean(
            StoreOrderRepository.class
        );
        return storeOrderRepository;
    }

    public static void changeOrderStatus(Paid paid) {
        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

    }

    public static void notifyCancel(OrderCanceled orderCanceled) {
        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

    }

    public static void copyOrder(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        StoreOrder storeOrder = new StoreOrder();
        repository().save(storeOrder);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(storeOrder->{
            
            storeOrder // do something
            repository().save(storeOrder);


         });
        */

    }
}
