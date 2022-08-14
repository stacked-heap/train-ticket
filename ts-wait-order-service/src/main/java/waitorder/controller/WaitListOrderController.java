package waitorder.controller;


import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waitorder.entity.WaitListOrderVO;
import waitorder.service.WaitListOrderService;

import static org.springframework.http.ResponseEntity.*;

/**
 * @author fdse
 */
@RestController
@RequestMapping("/api/v1/waitorderservice")
public class WaitListOrderController {

    @Autowired
    private WaitListOrderService waitListOrderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitListOrderController.class);

    @GetMapping(path = "/welcome")
    public String home() {
        return "Welcome to [ Wait Order Service ] !";
    }

    @PostMapping(path = "/order")
    public HttpEntity createNewOrder(@RequestBody WaitListOrderVO createOrder, @RequestHeader HttpHeaders headers) {
        WaitListOrderController.LOGGER.info("[createWaitOrder][Create Wait Order][from {} to {} at {}]", createOrder.getFrom(), createOrder.getTo(), createOrder.getDate());
        Response response = waitListOrderService.create(createOrder, headers);
        if (response.getStatus() == 0)
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        else
            return ok(response);
    }

    @GetMapping(path = "/orders")
    public HttpEntity getAllOrders(@RequestHeader HttpHeaders headers){
        LOGGER.info("[getAllOrders][Get All Orders]");
//        return ok(waitListOrderService.getAllOrders(headers));
        Response response =waitListOrderService.getAllOrders(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/waitlistorders")
    public HttpEntity getWaitListOrders(@RequestHeader HttpHeaders headers){
        LOGGER.info("[getWaitListOrders][Get All Wait List Orders]");
//        return ok(waitListOrderService.getAllWaitListOrders(headers));
        Response response =waitListOrderService.getAllWaitListOrders(headers);
        if (response.getStatus() == 1)
            return ok(response);
        else
            return new ResponseEntity(response,HttpStatus.NOT_FOUND);
    }


}
