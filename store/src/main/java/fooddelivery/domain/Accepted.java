package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Accepted extends AbstractEvent {

    private Long id;
    private String foodId;
    private Long orderId;
    private String status;
    private String address;
    private Integer qty;

    public Accepted(StoreOrder aggregate) {
        super(aggregate);
    }

    public Accepted() {
        super();
    }
}
