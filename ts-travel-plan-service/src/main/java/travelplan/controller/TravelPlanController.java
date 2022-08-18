package travelplan.controller;

import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.fudan.common.entity.TripInfo;
import travelplan.entity.TransferTravelInfo;
import travelplan.service.TravelPlanService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * @author fdse
 */
@RestController
@RequestMapping("api/v1/travelplanservice")
public class TravelPlanController {

    @Autowired
    TravelPlanService travelPlanService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelPlanController.class);

    @GetMapping(path = "/welcome" )
    public String home() {
        return "Welcome to [ TravelPlan Service ] !";
    }

    @PostMapping(value="/travelPlan/transferResult" )
    public HttpEntity getTransferResult(@RequestBody TransferTravelInfo info, @RequestHeader HttpHeaders headers) {
        TravelPlanController.LOGGER.info("[getTransferSearch][Search Transit][start: {},end: {}]",info.getStartStation(),info.getEndStation());
        return ok(travelPlanService.getTransferSearch(info, headers));
//        Response response =travelPlanService.getTransferSearch(info, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value="/travelPlan/cheapest")
    public HttpEntity getByCheapest(@RequestBody TripInfo queryInfo, @RequestHeader HttpHeaders headers) {
        TravelPlanController.LOGGER.info("[getCheapest][Search Cheapest][start: {},end: {},time: {}]",queryInfo.getStartPlace(),queryInfo.getEndPlace(),queryInfo.getDepartureTime());
        return ok(travelPlanService.getCheapest(queryInfo, headers));
//        Response response =travelPlanService.getCheapest(queryInfo, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value="/travelPlan/quickest")
    public HttpEntity getByQuickest(@RequestBody TripInfo queryInfo, @RequestHeader HttpHeaders headers) {
        TravelPlanController.LOGGER.info("[getQuickest][Search Quickest][start: {},end: {},time: {}]",queryInfo.getStartPlace(),queryInfo.getEndPlace(),queryInfo.getDepartureTime());
        return ok(travelPlanService.getQuickest(queryInfo, headers));
//        Response response =travelPlanService.getQuickest(queryInfo, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value="/travelPlan/minStation")
    public HttpEntity getByMinStation(@RequestBody TripInfo queryInfo, @RequestHeader HttpHeaders headers) {
        TravelPlanController.LOGGER.info("[getMinStation][Search Min Station][start: {},end: {},time: {}]",queryInfo.getStartPlace(),queryInfo.getEndPlace(),queryInfo.getDepartureTime());
        return ok(travelPlanService.getMinStation(queryInfo, headers));
//        Response response =travelPlanService.getMinStation(queryInfo, headers);
//        if (response.getStatus() == 1)
//            return ok(response);
//        else
//            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

}
