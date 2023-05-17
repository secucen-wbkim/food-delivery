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
    public void onPostPersist() {}

    @PrePersist
    public void onPrePersist() {}

    @PreUpdate
    public void onPreUpdate() {}

    @PreRemove
    public void onPreRemove() {}

    public static StoreOrderRepository repository() {
        StoreOrderRepository storeOrderRepository = StoreApplication.applicationContext.getBean(
            StoreOrderRepository.class
        );
        return storeOrderRepository;
    }

    public void finishCook() {
        if(this.getStatus().equals("조리 시작됨")){
            this.setStatus("조리 완료됨");
            repository().save(this);
            CookFinished cookFinished = new CookFinished(this);
            cookFinished.publishAfterCommit();
        }else{
            throw new RuntimeException("아직 조리 시작하지 않은 주문입니다.");
        }
    }

    public void accept() {
        if(this.getStatus().equals("결제됨")){
            this.setStatus("주문 승인됨");
            repository().save(this);
            Accepted accepted = new Accepted(this);
            accepted.publishAfterCommit();
        }else{
            throw new RuntimeException("아직 결제되지 않은 주문입니다.");
        }
    }

    public void reject() {
        if(this.getStatus().equals("결제됨")){
            this.setStatus("주문 거절됨");
            repository().save(this);
            Rejected rejected = new Rejected(this);
            rejected.publishAfterCommit();
        }else{
            throw new RuntimeException("아직 결제되지 않은 주문입니다.");
        }
    }

    public void startcook() {
        if(this.getStatus().equals("주문 승인됨")){
            this.setStatus("조리 시작됨");
            repository().save(this);
            CookStarted cookStarted = new CookStarted(this);
            cookStarted.publishAfterCommit();
        }else{
            throw new RuntimeException("아직 승인되지 않은 주문입니다.");
        }
    }

    public static void changeOrderStatus(Paid paid) {
        repository().findByOrderId(paid.getOrderId()).ifPresent(storeOrder->{
            storeOrder.setStatus("결제됨");
            repository().save(storeOrder);
         });

    }

    public static void notifyCancel(OrderCanceled orderCanceled) {        
        repository().findByOrderId(orderCanceled.getId()).ifPresent(storeOrder->{
            storeOrder.setStatus("주문 취소됨");
            repository().save(storeOrder);
         });

    }

    public static void copyOrder(OrderPlaced orderPlaced) {
        StoreOrder storeOrder = new StoreOrder();
        storeOrder.setOrderId(orderPlaced.getId());
        storeOrder.setQty(orderPlaced.getQty());
        storeOrder.setStatus("주문 생성됨");
        storeOrder.setFoodId(orderPlaced.getFoodId());
        storeOrder.setAddress(orderPlaced.getAddress());
        repository().save(storeOrder);
    }
}
