package Q3Project.DataAPIApplication.Controller;

import Q3Project.DataAPIApplication.Interface.IMachineAndComponentService;
import Q3Project.DataAPIApplication.Model.MonitoringData202009;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MonitoringDataController {

    @Autowired
    private IMachineAndComponentService<MonitoringData202009> service;

    @GetMapping("/monitoringdata")
    public List<MonitoringData202009> GetAllMonitoringData(){
        return service.GetAll();
    }

}
