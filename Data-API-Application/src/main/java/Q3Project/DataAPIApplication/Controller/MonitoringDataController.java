package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.IMonitoringDataService;
import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import Q3Project.DataAPIApplication.Model.ReturnableMonitoringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class MonitoringDataController {

    @Autowired
    private IMonitoringDataService service;

    @GetMapping("/monitoringdatas/{name}")
    public List<MonitoringData202009> GetAllMonitoringData(@PathVariable(value = "name") String name){
        return service.GetAllFromMachine(name);
    }

    @GetMapping("/monitoringdata/{machinename}/{datetime}")
    public List<MonitoringData202009> GetAllMonitoringDataPerDay(@PathVariable(value = "machinename") String machineName, @PathVariable(value = "datetime") String datetime) throws ParseException {
        return service.GetAllFromMachinePerDay(machineName, datetime);
    }

//    @GetMapping("/lastshot/{name}/{datetime}")
//    public MonitoringData202009 GetLastShotTimeFromMachine(@PathVariable(value = "datetime")String dateTime, @PathVariable(value = "name")String name){
//
//    }

}
