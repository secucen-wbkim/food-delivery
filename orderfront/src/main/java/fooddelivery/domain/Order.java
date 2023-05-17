package fooddelivery.domain;

import fooddelivery.OrderfrontApplication;
import fooddelivery.domain.OrderCanceled;
import fooddelivery.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String foodId;

    private String customerId;

    private String options;

    private String address;

    private String status;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        // fooddelivery.external.Payment payment = new fooddelivery.external.Payment();
        // payment.setOrderId(this.getId());
        // // mappings goes here
        // OrderfrontApplication.applicationContext
        //     .getBean(fooddelivery.external.PaymentService.class)
        //     .pay(payment);

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        if(this.getStatus().equals("주문됨") || this.getStatus().equals("결제됨") || this.getStatus().equals("주문 접수됨")){
            OrderCanceled orderCanceled = new OrderCanceled(this);
            orderCanceled.publishAfterCommit();
        }else{
            throw new RuntimeException("주문 취소가 불가능한 상태입니다.");
        }
    }

    @PrePersist
    public void onPrePersist() {}

    @PreRemove
    public void onPreRemove() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = OrderfrontApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }

    public void order() {
        //
    }

    public static void updateStatus(Accepted accepted) {
        repository().findById(accepted.getOrderId()).ifPresent(order->{
            order.setStatus("주문 접수됨");
            repository().save(order);
         });

    }

    public static void updateStatus(Paid paid) {
        repository().findById(paid.getOrderId()).ifPresent(order->{
            order.setStatus("결제됨");
            repository().save(order);
         });
    }

    public static void updateStatus(Rejected rejected) {
        repository().findById(rejected.getOrderId()).ifPresent(order->{
            order.setStatus("주문 거절됨");
            repository().save(order);


         });
    }
    
    public static void updateStatus(CookStarted cookStarted) {
        repository().findById(cookStarted.getOrderId()).ifPresent(order->{
            order.setStatus("조리중");
            repository().save(order);
         });
        

    }
}
