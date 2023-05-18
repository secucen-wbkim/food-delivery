package fooddelivery.infra;

import fooddelivery.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value="/storeOrders")
@Transactional
public class StoreOrderController {

    @Autowired
    StoreOrderRepository storeOrderRepository;

    @RequestMapping(
        value = "storeOrders/{id}/finish-cook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public StoreOrder finishCook(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /storeOrder/finishCook  called #####");
        Optional<StoreOrder> optionalStoreOrder = storeOrderRepository.findById(
            id
        );

        optionalStoreOrder.orElseThrow(() -> new Exception("No Entity Found"));
        StoreOrder storeOrder = optionalStoreOrder.get();
        storeOrder.finishCook();

        storeOrderRepository.save(storeOrder);
        return storeOrder;
    }

    @RequestMapping(
        value = "storeOrders/{id}/accept",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public StoreOrder accept(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /storeOrder/accept  called #####");
        Optional<StoreOrder> optionalStoreOrder = storeOrderRepository.findById(
            id
        );

        optionalStoreOrder.orElseThrow(() -> new Exception("No Entity Found"));
        StoreOrder storeOrder = optionalStoreOrder.get();
        storeOrder.accept();

        storeOrderRepository.save(storeOrder);
        return storeOrder;
    }

    @RequestMapping(
        value = "storeOrders/{id}/reject",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public StoreOrder reject(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /storeOrder/reject  called #####");
        Optional<StoreOrder> optionalStoreOrder = storeOrderRepository.findById(
            id
        );

        optionalStoreOrder.orElseThrow(() -> new Exception("No Entity Found"));
        StoreOrder storeOrder = optionalStoreOrder.get();
        storeOrder.reject();

        storeOrderRepository.save(storeOrder);
        return storeOrder;
    }

    @RequestMapping(
        value = "storeOrders/{id}/startcook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public StoreOrder startcook(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /storeOrder/startcook  called #####");
        Optional<StoreOrder> optionalStoreOrder = storeOrderRepository.findById(
            id
        );

        optionalStoreOrder.orElseThrow(() -> new Exception("No Entity Found"));
        StoreOrder storeOrder = optionalStoreOrder.get();
        storeOrder.startcook();

        storeOrderRepository.save(storeOrder);
        return storeOrder;
    }
}
