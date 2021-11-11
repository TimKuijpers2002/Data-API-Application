package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.IMonitoringDataService;
import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
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

    @GetMapping("/monitoringdatas/{board}/{port}")
    public List<MonitoringData202009> GetAllMonitoringData(@PathVariable(value = "board") int board, @PathVariable(value = "port") int port){
        return service.GetAllFromMachine(board, port);
    }

    @GetMapping("/monitoringdata/{board}/{port}/{datetime}")
    public List<MonitoringData202009> GetAllMonitoringDataPerDay(@PathVariable(value = "board") int board, @PathVariable(value = "port") int port, @PathVariable(value = "datetime") String datetime) throws ParseException {
        return service.GetAllFromMachinePerDay(board, port, datetime);
    }

}