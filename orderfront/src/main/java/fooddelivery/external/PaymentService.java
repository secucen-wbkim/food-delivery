package fooddelivery.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "orderfront", url = "${api.url.orderfront}")
public interface PaymentService {
    @RequestMapping(method = RequestMethod.POST, path = "/payments")
    public void pay(@RequestBody Payment payment);
}
