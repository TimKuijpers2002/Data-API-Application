package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.IMonitoringDataService;
import Q3Project.DataAPIApplication.Interface.ITreeviewService;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class MonitoringDataController {

//    @Autowired
//    private IMonitoringDataService service;
//
//    @GetMapping("/monitoringdata")
//    public List<MonitoringData202009> GetAllMonitoringData(){
//        return service.GetAllFromMachines();
//    }

}
