package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.IMonitoringDataService;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class MonitoringDataController {

    @Autowired
    private IMonitoringDataService service;

    @Transactional
    @GetMapping("/monitoringdatas/{name}")
    public List<MonitoringData202009> GetAllMonitoringData(@PathVariable(value = "name") String name){
        return service.GetAllFromMachine(name);
    }

    @Transactional
    @GetMapping("/monitoringdata/{machinename}/{datetime}")
    public List<MonitoringData202009> GetAllMonitoringDataPerDay(@PathVariable(value = "machinename") String machineName, @PathVariable(value = "datetime") String datetime) throws ParseException {
        return service.GetAllFromMachinePerDay(machineName, datetime);
    }

    @Transactional
    @GetMapping("/machinestatebar/{name}/{datetime}")
    public List<MonitoringData202009> GetAllUpAndDownTimes(@PathVariable(value = "datetime")String dateTime, @PathVariable(value = "name")String name) throws ParseException {
        return service.GetAllUpAndDownTimes(name, dateTime);
    }

    @Transactional
    @GetMapping("/machinestate/{name}/{datetime}")
    public boolean GetMachineStateOnTimestamp(@PathVariable(value = "datetime")String dateTime, @PathVariable(value = "name")String name) throws ParseException {
        return service.CheckCurrentMachineState(name, dateTime);
    }
}
